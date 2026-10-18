package com.apollo.payment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Random;
import com.apollo.common.event.PaymentSuccessEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;


@Service
public class PaymentServiceImpl implements PaymentService {

    private static final String TOPIC = "payment-success";

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;


    private static final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);



    // In-memory store for payment records (replace with a real DB in production)

    private static final Random random = new Random();

    public static boolean getRandomBoolean() {
        return random.nextBoolean();
    }

    @Override
    public Map<String, Object> getPayment(Long appointmentId) {
        Map<String, Object> status = Map.of("status", getRandomBoolean(), "appointmentId", appointmentId);
        boolean paymentStatus = (boolean) status.get("status");
        if (paymentStatus) {
            PaymentSuccessEvent event = new PaymentSuccessEvent(appointmentId, Instant.now());
            kafkaTemplate.send(TOPIC, String.valueOf(appointmentId), event);
            System.out.println("Payment successful, notification triggered");
        } else {
            System.out.println("Payment failed, no notification sent");
        }
        return status;
    }
}
