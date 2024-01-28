package com.careridetransport.server.service;

import com.careridetransport.server.model.Payment;
import com.careridetransport.server.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> findAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment findPaymentById(Long id) {
        return paymentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Payment not found"));
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Update the payment details
        payment.setAmount(paymentDetails.getAmount());
        payment.setStatus(paymentDetails.getStatus());
        // ...other fields to update

        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new RuntimeException("Payment not found");
        }
        paymentRepository.deleteById(id);
    }
}
