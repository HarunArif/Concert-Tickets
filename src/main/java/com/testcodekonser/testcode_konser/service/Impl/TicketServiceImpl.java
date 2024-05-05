package com.testcodekonser.testcode_konser.service.Impl;

import com.testcodekonser.testcode_konser.entity.Day;
import com.testcodekonser.testcode_konser.entity.LineUp;
import com.testcodekonser.testcode_konser.entity.Ticket;
import com.testcodekonser.testcode_konser.model.request.TicketRequest;
import com.testcodekonser.testcode_konser.repository.TicketRepository;
import com.testcodekonser.testcode_konser.service.DayService;
import com.testcodekonser.testcode_konser.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final DayService dayService;

    @Override
    @Transactional
    public Ticket purchaseTicket(TicketRequest ticketRequest) {
        Day day = dayService.getDayById(ticketRequest.getDayId());
        double price = day.getPrice();
        Ticket ticket = Ticket.builder()
                .day(day)
                .price(price)
                .build();
        return ticketRepository.save(ticket);
    }

    @Override
    public Page<Ticket> getAllTicket(Integer page, Integer size) {
        if (page <=0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page-1, size);
        return ticketRepository.findAll(pageable);
    }

    @Override
    public Ticket getById(String id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) return optionalTicket.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket with id : " + id + " Not Found");
    }

}
