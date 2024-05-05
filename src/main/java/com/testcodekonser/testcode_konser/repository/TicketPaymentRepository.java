package com.testcodekonser.testcode_konser.repository;

import com.testcodekonser.testcode_konser.entity.TicketPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketPaymentRepository extends JpaRepository<TicketPayment, String> {
}
