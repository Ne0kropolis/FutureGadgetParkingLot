package com.FutureGadgetParkingLot.data;

import com.FutureGadgetParkingLot.domain.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JDBC Implementation of DAO for Ticket Domain Object.
 */
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
        jdbcTemplate.update(query, ticket.getTicketId(), ticket.getLotId(), ticket.getTimeIn(), ticket.getTimeOut(), ticket.getPrice(), ticket.getLost());
    }

    @Override
    public void batchInsert(List<Ticket> ticketList) throws SQLException {
        String query = "INSERT INTO TICKET VALUES(?,?,?,?,?,?);";
        batchTicketSetValues(ticketList, query);
    }

    /**
     * Batch update for a list of tickets after their prices are calculated.
     * @param ticketList
     * @throws SQLException
     */
    public void batchUpdate(List<Ticket> ticketList) throws SQLException {
        String query = "UPDATE TICKET SET Lot_Id=?, Ticket_Date=?, Ticket_Time_In=?, Ticket_Time_Out=?, Ticket_Price=?, Ticket_Lost=? Where Ticket_Id = ?;";
        batchTicketSetValues(ticketList, query);
    }

    @Override
    public void update(Ticket ticket) {
        String query = "UPDATE TICKET SET Lot_Id=?, Ticket_Date=?, Ticket_Time_In=?, Ticket_Time_Out=?, Ticket_Price=?, Ticket_Lost=? Where Ticket_Id = ?";
        jdbcTemplate.update(query,
                ticket.getLotId(),
                ticket.getTimeIn(),
                ticket.getTimeOut(),
                ticket.getPrice(),
                ticket.getLost(),
                ticket.getTicketId()
        );
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM TICKET WHERE Ticket_ID = ?;";
        this.jdbcTemplate.update(query, id);
    }

    /**
     * Shared setValues Implementation for batch inserts and updates.
     * @param ticketList
     * @param query: query string for update or insert.
     * @throws SQLException
     */
    private void batchTicketSetValues(List<Ticket> ticketList, String query) throws SQLException {
        this.jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, ticketList.get(i).getTicketId());
                preparedStatement.setInt(2, ticketList.get(i).getLotId());
                preparedStatement.setTimestamp(4, ticketList.get(i).getTimeIn());
                preparedStatement.setTimestamp(4, ticketList.get(i).getTimeOut());
                preparedStatement.setDouble(5, ticketList.get(i).getPrice());
                preparedStatement.setBoolean(6, ticketList.get(i).getLost());
            }

            @Override
            public int getBatchSize() {
                return ticketList.size();
            }
        });
    }

    /**
     * Rowmapper for Ticket queries.
     */
    public class TicketMapper implements RowMapper<Ticket> {

        public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Ticket(
                    rs.getInt("Ticket_id"),
                    rs.getInt("Lot_id"),
                    rs.getTimestamp("Ticket_time_in"),
                    rs.getTimestamp("Ticket_time_out"),
                    rs.getDouble("Ticket_Price"),
                    rs.getBoolean("Ticket_Lost"));
        }
    }
}
