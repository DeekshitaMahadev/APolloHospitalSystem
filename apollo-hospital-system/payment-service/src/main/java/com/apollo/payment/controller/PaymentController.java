package com.apollo.payment.controller;

import com.apollo.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    @GetMapping("/{appointmentId}")
    public Map<String, Object> getPayment(@PathVariable Long appointmentId) {
        return paymentService.getPayment(appointmentId);
    }
}
