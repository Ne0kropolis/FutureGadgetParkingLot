package com.FutureGadgetParkingLot.data;

import com.FutureGadgetParkingLot.domain.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        return ((Lot) jdbct.queryForObject(query, new LotMapper ()));
    }

    @Override
    public List<Lot> getAll() {
        String query = "SELECT * FROM LOT;";
        return (jdbct.query(query, new LotMapper()));
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
                lot.getLotCapactiy()
        );
    }

    @Override
    public void update(Lot lot) {
        String query = "UPDATE LOT SET Lot_Id=?, Pricing_Scheme_Number=?, Lot_Name=?, Lot_Address=?, Lot_Capacity=? WHERE Lot_Id=?";
        jdbct.update(
                query,
                lot.getLotId(),
                lot.getPricingSchemeNumber(),
                lot.getLotName(),
                lot.getLotAddress(),
                lot.getLotCapactiy(),
                lot.getLotId()
        );
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM LOT WHERE Lot_Id=?";
        jdbct.update(query, id);
    }

    public class LotMapper implements RowMapper<Lot> {
        public Lot mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Lot(
                    rs.getInt("Lot_Id"),
                    rs.getInt("Pricing_Scheme_Number"),
                    rs.getString("Lot_Name"),
                    rs.getString("Lot_Address"),
                    rs.getInt("Lot_Capacity"));
        }
    }
}
