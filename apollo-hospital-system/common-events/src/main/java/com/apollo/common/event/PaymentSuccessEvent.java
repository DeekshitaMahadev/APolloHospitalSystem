package com.apollo.common.event;

import java.time.Instant;

public class PaymentSuccessEvent {
    private Long appointmentId;
    private Instant timestamp;

    public PaymentSuccessEvent(Long appointmentId, Instant timestamp) {
        this.appointmentId = appointmentId;
        this.timestamp = timestamp;
    }
    public PaymentSuccessEvent() {

    }

    public Long getAppointmentId() { return appointmentId; }
    public Instant getTimestamp() { return timestamp; }
}
