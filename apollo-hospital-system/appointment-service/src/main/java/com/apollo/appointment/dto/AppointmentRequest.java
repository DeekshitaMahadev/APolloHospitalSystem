package com.apollo.appointment.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AppointmentRequest {

    @NotNull(message = "patientId is required")
    private Long patientId;

    @NotBlank(message = "patientName is required")
    private String patientName;

    @NotNull(message = "doctorId is required")
    private Long doctorId;

    @NotBlank(message = "department is required")
    private String department;

    @NotNull(message = "appointmentTime is required")
    @Future(message = "appointmentTime must be in the future")
    private LocalDateTime appointmentTime;

    // Optional: only used on update (e.g. CONFIRMED, CANCELLED). Ignored on create.
    private String status;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
