//package com.apollo.appointment.event;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//public class AppointmentCreatedEvent implements Serializable {
//
//    private Long appointmentId;
//    private Long patientId;
//    private String patientName;
//    private Long doctorId;
//    private String department;
//    private LocalDateTime appointmentTime;
//    private LocalDateTime createdAt;
//
//    public AppointmentCreatedEvent() {
//    }
//
//    public AppointmentCreatedEvent(Long appointmentId, Long patientId, String patientName,
//                                    Long doctorId, String department,
//                                    LocalDateTime appointmentTime, LocalDateTime createdAt) {
//        this.appointmentId = appointmentId;
//        this.patientId = patientId;
//        this.patientName = patientName;
//        this.doctorId = doctorId;
//        this.department = department;
//        this.appointmentTime = appointmentTime;
//        this.createdAt = createdAt;
//    }
//
//    public Long getAppointmentId() { return appointmentId; }
//    public void setAppointmentId(Long appointmentId) { this.appointmentId = appointmentId; }
//
//    public Long getPatientId() { return patientId; }
//    public void setPatientId(Long patientId) { this.patientId = patientId; }
//
//    public String getPatientName() { return patientName; }
//    public void setPatientName(String patientName) { this.patientName = patientName; }
//
//    public Long getDoctorId() { return doctorId; }
//    public void setDoctorId(Long doctorId) { this.doctorId = doctorId; }
//
//    public String getDepartment() { return department; }
//    public void setDepartment(String department) { this.department = department; }
//
//    public LocalDateTime getAppointmentTime() { return appointmentTime; }
//    public void setAppointmentTime(LocalDateTime appointmentTime) { this.appointmentTime = appointmentTime; }
//
//    public LocalDateTime getCreatedAt() { return createdAt; }
//    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
//}
