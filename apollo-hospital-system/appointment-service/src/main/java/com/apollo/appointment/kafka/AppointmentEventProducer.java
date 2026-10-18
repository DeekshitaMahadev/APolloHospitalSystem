//package com.apollo.appointment.kafka;
//
//import com.apollo.appointment.event.AppointmentCreatedEvent;
//import com.apollo.appointment.event.KafkaTopics;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AppointmentEventProducer {
//
//    private static final Logger log = LoggerFactory.getLogger(AppointmentEventProducer.class);
//
//    private final KafkaTemplate<String, Object> kafkaTemplate;
//
//    public AppointmentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void publishAppointmentCreated(AppointmentCreatedEvent event) {
//        // Keying by appointmentId keeps all events for the same appointment
//        // on the same partition, preserving per-appointment ordering.
//        String key = String.valueOf(event.getAppointmentId());
//        kafkaTemplate.send(KafkaTopics.APPOINTMENT_CREATED, key, event)
//                .whenComplete((result, ex) -> {
//                    if (ex != null) {
//                        log.error("Failed to publish AppointmentCreatedEvent for appointmentId={}",
//                                event.getAppointmentId(), ex);
//                    } else {
//                        log.info("Published AppointmentCreatedEvent for appointmentId={} to partition={}",
//                                event.getAppointmentId(), result.getRecordMetadata().partition());
//                    }
//                });
//    }
//}
