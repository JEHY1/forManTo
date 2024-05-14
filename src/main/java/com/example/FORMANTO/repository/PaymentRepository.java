package com.example.FORMANTO.repository;

import com.example.FORMANTO.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
