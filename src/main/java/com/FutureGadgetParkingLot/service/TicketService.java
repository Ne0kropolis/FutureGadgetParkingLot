package com.FutureGadgetParkingLot.service;

import com.FutureGadgetParkingLot.data.Ticket_JDBC_DAO;
import com.FutureGadgetParkingLot.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class TicketService {

    private final Ticket_JDBC_DAO ticketJdbcDao;
    private TicketPriceCalculationService ticketPriceCalculationService;
    private DateCalculationService dateCalculationService;

    @Autowired
    public TicketService(@Qualifier("ticket_JDBC_DAO") Ticket_JDBC_DAO ticketJdbcDao) {
        this.ticketJdbcDao = ticketJdbcDao;
    }

    @Autowired
    public void setTicketPriceCalculationService(TicketPriceCalculationService ticketPriceCalculationService) {
        this.ticketPriceCalculationService = ticketPriceCalculationService;
    }

    @Autowired
    public void setDateCalculationService(DateCalculationService dateCalculationService) {
        this.dateCalculationService = dateCalculationService;
    }

    public List getAllTickets() {
        return (this.ticketJdbcDao.getAll());
    }

    public Ticket getTicketById(int id) { return (this.ticketJdbcDao.get(id)); }

    public List calculateAllOutstandingTickets() throws SQLException {
        List<Ticket> ticketList = ticketJdbcDao.getAll();
        for (int i=0; i<ticketList.size();i++) {
            if (ticketList.get(i).getPrice() == 0) {
                ticketList.get(i).setPrice(ticketPriceCalculationService.calculateTicketPrice(
                        dateCalculationService.calculateDuration(ticketList.get(i)), ticketList.get(i).getLotId(), ticketList.get(i).getLost()));
            }
        }
        ticketJdbcDao.batchUpdate(ticketList);
        return ticketList;
    }

    public Ticket calculateOutstandingTicket(int id) {
        Ticket ticket = ticketJdbcDao.get(id);
        if (ticket.getPrice() == 0) {
            ticket.setPrice(ticketPriceCalculationService.calculateTicketPrice(dateCalculationService.calculateDuration(ticket), ticket.getLotId(), ticket.getLost()));
        }
        ticketJdbcDao.update(ticket);
        return ticket;
    }

    public void createTicket(Ticket ticket) { this.ticketJdbcDao.insert(ticket); }

    public void createTickets(List<Ticket> ticketList) throws SQLException {this.ticketJdbcDao.batchInsert(ticketList);}

    public void updateTicket(Ticket ticket) { this.ticketJdbcDao.update(ticket); }

    public void deleteTicket(int id) {ticketJdbcDao.delete(id);}

}
