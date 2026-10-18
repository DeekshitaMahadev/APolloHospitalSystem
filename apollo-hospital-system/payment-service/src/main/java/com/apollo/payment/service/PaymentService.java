package com.apollo.payment.service;


import java.util.Map;

public interface PaymentService {

    /**
     * Process payment triggered by a Kafka AppointmentCreatedEvent.
     * Simulates payment and publishes a PaymentProcessedEvent.
     */
    /**
     * Look up payment details by appointment ID (REST query).
     */
    Map<String, Object> getPayment(Long appointmentId);
}
