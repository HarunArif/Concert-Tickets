package com.testcodekonser.testcode_konser.service;

import com.testcodekonser.testcode_konser.entity.LineUp;
import com.testcodekonser.testcode_konser.entity.Ticket;
import com.testcodekonser.testcode_konser.model.request.TicketRequest;
import org.springframework.data.domain.Page;

public interface TicketService {
    Ticket purchaseTicket(TicketRequest ticketRequest);
    Page<Ticket> getAllTicket(Integer page, Integer size);
    Ticket getById(String id);

}
