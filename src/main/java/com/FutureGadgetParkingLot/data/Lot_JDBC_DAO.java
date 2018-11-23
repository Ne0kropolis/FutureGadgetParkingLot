package com.FutureGadgetParkingLot.data;

import com.FutureGadgetParkingLot.domain.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Implementation of DAO for Lot Domain Object.
 */
@Repository
public class Lot_JDBC_DAO implements DAO<Lot> {
    private DataSource dataSource;
    private JdbcTemplate jdbct;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbct = new JdbcTemplate(dataSource);
    }

    @Override
    public Lot get(int id) {
        String query = "SELECT * FROM  LOT WHERE Lot_Id = " + id + ";";
        return ( jdbct.queryForObject(query, new LotMapper ()));
    }

    @Override
    public List<Lot> getAll() {
        String query = "SELECT * FROM LOT;";
        return (jdbct.query(query, new LotMapper()));
    }

    /**
     * Retrieves a pricing scheme number for a given lot id.
     * @param id: lotId
     * @return int pricingSchemeNumber
     */
    public Integer getPricingSchemeNumber(int id) {
        String query = "SELECT * FROM LOT WHERE Lot_Id = " + id;
        return Objects.requireNonNull(this.jdbct.queryForObject(query, new LotMapper())).getPricingSchemeNumber();
    }

    @Override
    public void insert(Lot lot) {
        String query = "INSERT INTO LOT VALUES (?,?,?,?,?)";
        jdbct.update(
                query,
                lot.getLotId(),
                lot.getPricingSchemeNumber(),
                lot.getLotName(),
                lot.getLotAddress(),
                lot.getlotCapacity()
        );
    }

    @Override
    public void batchInsert(List<Lot> lotList) {
        String query = "INSERT INTO LOT VALUES (?,?,?,?,?);";
        this.jdbct.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, lotList.get(i).getLotId());
                preparedStatement.setInt(2, lotList.get(i).getPricingSchemeNumber());
                preparedStatement.setString(3, lotList.get(i).getLotName());
                preparedStatement.setString(4, lotList.get(i).getLotAddress());
                preparedStatement.setInt(5, lotList.get(i).getlotCapacity());
            }

            @Override
            public int getBatchSize() {
                return lotList.size();
            }
        });
    }


    @Override
    public void update(Lot lot) {
        String query = "UPDATE LOT SET Pricing_Scheme_Number=?, Lot_Name=?, Lot_Address=?, Lot_Capacity=? WHERE Lot_Id=?";
        jdbct.update(
                query,
                lot.getPricingSchemeNumber(),
                lot.getLotName(),
                lot.getLotAddress(),
                lot.getlotCapacity(),
                lot.getLotId()
        );
    }

    /**
     * Change capacity for a given lot.
     * @param id: lotId
     * @param capacity: new capacity
     */
    public void updateCapacity(int id, int capacity) {
        String query = "UPDATE LOT SET Lot_Capacity=? WHERE Lot_Id=?";
        jdbct.update(query, capacity, id);
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM LOT WHERE Lot_Id=?";
        jdbct.update(query, id);
    }

    /**
     * Rowmapper for Lot queries
     */
    public class LotMapper implements RowMapper<Lot> {
        public Lot mapRow(ResultSet rs, final int rowNum) throws SQLException {
            return new Lot(
                    rs.getInt("Lot_Id"),
                    rs.getInt("Pricing_Scheme_Number"),
                    rs.getString("Lot_Name"),
                    rs.getString("Lot_Address"),
                    rs.getInt("Lot_Capacity"));
        }
    }
}
