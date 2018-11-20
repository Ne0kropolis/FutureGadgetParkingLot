package com.FutureGadgetParkingLot.data;

import com.FutureGadgetParkingLot.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class Ticket_JDBC_DAO implements DAO<Ticket> {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Ticket get(int id) {
        String query = "SELECT * FROM TICKET WHERE Ticket_Id = " + id;
        return (jdbcTemplate.queryForObject(query, new TicketMapper()));
    }

    @Override
    public List getAll() {
        String query = "SELECT * FROM TICKET";
        return (jdbcTemplate.query(query, new TicketMapper()));
    }

    @Override
    public void insert(Ticket ticket) {
        String query = "INSERT INTO TICKET VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(query, ticket.getTicketId(), ticket.getLotId(), ticket.getDate(), ticket.getTimeIn(), ticket.getTimeOut(), ticket.getPrice());
    }

    @Override
    public void update(Ticket ticket) {
        String query = "UPDATE TICKET SET price=? Where Ticket_Id = ?";
        jdbcTemplate.update(query,ticket.getPrice(), ticket.getTicketId());
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM TICKET WHERE Ticket_Id =" + id + ";";
    }

    public class TicketMapper implements RowMapper<Ticket> {
        public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Ticket(
                    rs.getInt("Ticket_id"),
                    rs.getInt("Lot_id"),
                    rs.getString("Ticket_date"),
                    rs.getString("Ticket_time_in"),
                    rs.getString("Ticket_time_out"),
                    rs.getDouble("Ticket_Price"),
                    rs.getBoolean("Ticket_Lost"));
        }
    }

    public String hello() {
        return ("Hello");
    }
}
