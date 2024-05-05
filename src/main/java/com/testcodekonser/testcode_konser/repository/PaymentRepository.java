package com.testcodekonser.testcode_konser.repository;

import com.testcodekonser.testcode_konser.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, String> {
}
