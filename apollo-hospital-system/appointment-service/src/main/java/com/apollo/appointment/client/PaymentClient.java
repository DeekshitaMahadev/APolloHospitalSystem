package com.apollo.appointment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(name = "payment-service")
public interface PaymentClient {

    @GetMapping("/payments/{appointmentId}")
    Map<String, Object> getPayment(@PathVariable("appointmentId") Long appointmentId);
}