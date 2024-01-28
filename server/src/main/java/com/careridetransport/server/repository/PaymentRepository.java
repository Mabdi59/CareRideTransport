package com.careridetransport.server.repository;

import com.careridetransport.server.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // You can define custom query methods here
}
