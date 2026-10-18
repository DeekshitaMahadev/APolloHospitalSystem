package com.apollo.appointment.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

public class AppointmentResponse {

    private Long id;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String department;
    private LocalDateTime appointmentTime;
    private String status;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    // Builds a response DTO from a raw DB row (List<Map<String, Object>> style, one row).
    public static AppointmentResponse fromRow(Map<String, Object> row) {
        AppointmentResponse response = new AppointmentResponse();
        response.id = toLong(row.get("id"));
        response.patientId = toLong(row.get("patient_id"));
        response.patientName = (String) row.get("patient_name");
        response.doctorId = toLong(row.get("doctor_id"));
        response.department = (String) row.get("department");
        response.appointmentTime = toLocalDateTime(row.get("appointment_time"));
        response.status = (String) row.get("status");
        response.createdOn = toLocalDateTime(row.get("created_on"));
        response.updatedOn = toLocalDateTime(row.get("updated_on"));
        return response;
    }

    private static Long toLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Long l) {
            return l;
        }
        if (value instanceof Number n) {
            return n.longValue();
        }
        return Long.valueOf(value.toString());
    }

    private static LocalDateTime toLocalDateTime(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof LocalDateTime ldt) {
            return ldt;
        }
        if (value instanceof Timestamp ts) {
            return ts.toLocalDateTime();
        }
        throw new IllegalArgumentException("Unsupported date/time type: " + value.getClass());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
