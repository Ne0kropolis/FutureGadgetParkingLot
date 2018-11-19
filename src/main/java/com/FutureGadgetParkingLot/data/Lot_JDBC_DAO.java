package com.FutureGadgetParkingLot.data;

import com.FutureGadgetParkingLot.domain.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        return ((Lot) jdbct.query(query, new LotMapper ()));
    }

    @Override
    public List<Lot> getAll() {
        return null;
    }

    @Override
    public void insert(Lot lot) {

    }

    @Override
    public void update(Lot lot) {

    }

    @Override
    public void delete(int id) {

    }

    public class LotMapper implements RowMapper<Lot> {
        public Lot mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Lot(
                    rs.getInt("Lot_Id"),
                    rs.getInt("PricingSchemeNumber"),
                    rs.getString("Lot_Name"),
                    rs.getString("Lot_Address"),
                    rs.getInt("Lot_Capacity"));
        }
    }
}
