package com.apollo.appointment.service;

import com.apollo.appointment.dto.AppointmentRequest;

import java.util.List;
import java.util.Map;

public interface AppointmentService {

    Map<String, Object> createAppointment(AppointmentRequest request);

    Map<String, Object> getAppointment(Long id);

    List<Map<String, Object>> getAllAppointments();

    Map<String, Object> updateAppointment(Long id, AppointmentRequest request);

    void deleteAppointment(Long id);
}
