package com.FutureGadgetParkingLot.service;

import com.FutureGadgetParkingLot.data.Ticket_JDBC_DAO;
import com.FutureGadgetParkingLot.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final Ticket_JDBC_DAO ticketJdbcDao;

    @Autowired
    public TicketService(@Qualifier("ticket_JDBC_DAO") Ticket_JDBC_DAO ticketJdbcDao) {
        this.ticketJdbcDao = ticketJdbcDao;
    }

    public List getAllTickets() {
        return (this.ticketJdbcDao.getAll());
    }

    public Ticket getSingleTicket(int id) {
        Ticket ticket = new Ticket(1, 100, "2018-11-09", "10:15", "11:20", 30, false);
        return (this.ticketJdbcDao.get(id));
    }


    public String getHello() {
        return "Hello";
    }
}
