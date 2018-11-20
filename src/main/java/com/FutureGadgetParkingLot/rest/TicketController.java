package com.FutureGadgetParkingLot.rest;

import com.FutureGadgetParkingLot.domain.Ticket;
import com.FutureGadgetParkingLot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }



    @Path("/get/all")
    @GET
    @Produces("application/json")
    public List getAllTickets() {
        return this.ticketService.getAllTickets();
    }


    @Path("/get/{id}")
    @GET
    @Produces("application/json")
    public Ticket getTicketById(@PathParam("id") int id) {
        return this.ticketService.getTicketById(id);
    }
}
