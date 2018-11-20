package com.FutureGadgetParkingLot.rest;

import com.FutureGadgetParkingLot.domain.Ticket;
import com.FutureGadgetParkingLot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.List;

@RestController
@RequestMapping("tickets/get")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    @Produces("application/json")
    public List getAllTickets() {
        return this.ticketService.getAllTickets();
    }

    @GetMapping("/one/{id}")
    @Produces("application/json")
    public Ticket getOneTicket(@PathVariable int id) {
        return this.ticketService.getSingleTicket(id);
    }
}
