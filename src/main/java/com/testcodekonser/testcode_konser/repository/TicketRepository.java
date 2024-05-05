package com.testcodekonser.testcode_konser.repository;

import com.testcodekonser.testcode_konser.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
