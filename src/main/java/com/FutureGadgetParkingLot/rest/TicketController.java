package com.FutureGadgetParkingLot.rest;

import com.FutureGadgetParkingLot.domain.Ticket;
import com.FutureGadgetParkingLot.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.sql.SQLException;
import java.util.List;

@Controller
@Path("/tickets")
public class TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Path("/all")
    @GET
    @Produces("application/json")
    public List getAllTickets() {
        return this.ticketService.getAllTickets();
    }

    @Path("/{id}")
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

    @Path("/outstanding")
    @GET
    @Produces("application/json")
    public List<Ticket> getAllOutstandingTickets() throws SQLException {
        return ticketService.calculateAllOutstandingTickets();
    }

    @Path("/outstanding/{id}")
    @GET
    @Produces("application/json")
    public Ticket getOutstandingTicket(@PathParam("id") int id) {
        return ticketService.calculateOutstandingTicket(id);
    }

    @Path("/create/list/json")
    @POST
    @Consumes("application/json")
    public void createTickets(List<Ticket> ticketList) throws SQLException { this.ticketService.createTickets(ticketList);}

    @Path("/update/json")
    @PUT
    @Consumes
    public void updateTicket(Ticket ticket) { this.ticketService.updateTicket(ticket);}

    @Path("/delete/{id}")
    @DELETE
    public void deleteTicket(@PathParam("id") int id) {this.ticketService.deleteTicket(id);}

}
