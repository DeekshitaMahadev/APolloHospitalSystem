package com.apollo.appointment.service;

import com.apollo.appointment.client.PaymentClient;
import com.apollo.appointment.dto.AppointmentRequest;
import com.apollo.appointment.event.AppointmentStatus;
import com.apollo.appointment.repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger log = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private final AppointmentRepository appointmentRepository;
    private final PaymentClient paymentClient;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                   PaymentClient paymentClient) {
        this.appointmentRepository = appointmentRepository;
        this.paymentClient = paymentClient;
    }

    @Override
    public Map<String, Object> createAppointment(AppointmentRequest request) {

        // Step 1: Prepare Appointment Data
        Map<String, Object> appointment = new LinkedHashMap<>();
        appointment.put("patientId", request.getPatientId());
        appointment.put("patientName", request.getPatientName());
        appointment.put("doctorId", request.getDoctorId());
        appointment.put("department", request.getDepartment());
        appointment.put("appointmentTime", request.getAppointmentTime());
        appointment.put("status", AppointmentStatus.PENDING.name());

        // Step 2: Save Appointment
        Map<String, Object> savedAppointment = appointmentRepository.save(appointment);

        // Step 3: Get Appointment ID
        Long appointmentId = ((Number) savedAppointment.get("id")).longValue();

        // Step 4: Call Payment Service via OpenFeign
        Map<String, Object> paymentResponse = paymentClient.getPayment(appointmentId);

        Object rawStatus = paymentResponse.get("status");
        boolean paymentSuccess;
        if (rawStatus instanceof Boolean) {
            paymentSuccess = (Boolean) rawStatus;
        } else {
            paymentSuccess = "SUCCESS".equals(String.valueOf(rawStatus));
        }

        // Step 5: Update Appointment Status
        String newStatus;
        if (paymentSuccess) {
            newStatus = AppointmentStatus.CONFIRMED.name();
            log.info("Payment SUCCESS for appointmentId={}", appointmentId);
        } else {
            newStatus = AppointmentStatus.FAILED.name();
            log.info("Payment FAILED for appointmentId={}", appointmentId);
        }

        // Step 6: Update status in DB
        appointmentRepository.updateAppointmentStatus(appointmentId.intValue(), newStatus);

        // Step 7: Return fresh appointment from DB
        return appointmentRepository.findById(appointmentId)
                .orElse(savedAppointment);
    }

    @Override
    public Map<String, Object> getAppointment(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Appointment not found: " + id));
    }

    @Override
    public List<Map<String, Object>> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Map<String, Object> updateAppointment(Long id, AppointmentRequest request) {
        if (!appointmentRepository.existsById(id)) {
            throw new NoSuchElementException("Appointment not found: " + id);
        }

        Map<String, Object> appointmentData = new LinkedHashMap<>();
        appointmentData.put("patientId", request.getPatientId());
        appointmentData.put("patientName", request.getPatientName());
        appointmentData.put("doctorId", request.getDoctorId());
        appointmentData.put("department", request.getDepartment());
        appointmentData.put("appointmentTime", request.getAppointmentTime());
        appointmentData.put("status", request.getStatus() != null ? request.getStatus() : AppointmentStatus.PENDING.name());

        return appointmentRepository.update(id, appointmentData)
                .orElseThrow(() -> new NoSuchElementException("Appointment not found: " + id));
    }

    @Override
    public void deleteAppointment(Long id) {
        boolean deleted = appointmentRepository.deleteById(id);
        if (!deleted) {
            throw new NoSuchElementException("Appointment not found: " + id);
        }
    }
}
