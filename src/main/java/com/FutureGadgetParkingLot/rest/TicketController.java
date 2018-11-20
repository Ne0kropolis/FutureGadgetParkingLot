package com.FutureGadgetParkingLot.rest;

import com.FutureGadgetParkingLot.domain.Ticket;
import com.FutureGadgetParkingLot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.util.List;

@Controller
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

    @Path("/create/json")
    @POST
    @Consumes("application/json")
    public void createTicket(Ticket ticket) {
        this.ticketService.createTicket(ticket);
    }
}
