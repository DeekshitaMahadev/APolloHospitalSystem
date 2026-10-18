package com.apollo.notification.listener;

import com.apollo.common.event.PaymentSuccessEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventListener {

    @KafkaListener(topics = "payment-success", groupId = "notification-group")
    public void handlePaymentSuccess(PaymentSuccessEvent event) {
        System.out.println("Payment succeeded, sending notification for appointment: " + event.getAppointmentId());
        sendNotification(event);
    }

    private void sendNotification(PaymentSuccessEvent event) {
        // your actual email/SMS/push logic
        System.out.println("Notification sent to user: " +event.getAppointmentId() + " at " + event.getTimestamp());
    }
}