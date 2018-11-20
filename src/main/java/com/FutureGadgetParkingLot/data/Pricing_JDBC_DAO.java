package com.FutureGadgetParkingLot.data;

import com.FutureGadgetParkingLot.domain.Lot;
import com.FutureGadgetParkingLot.domain.Pricing;
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

@Repository
public class Pricing_JDBC_DAO implements DAO<Pricing> {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Pricing get(int id) {
        String query = "SELECT * FROM PRICING WHERE Pricing_Id = " + id + ";";
        return (jdbcTemplate.queryForObject(query, new PricingMapper()));
    }

    @Override
    public List<Pricing> getAll() {
        String query = "SELECT * FROM PRICING";
        return (jdbcTemplate.query(query, new PricingMapper()));
    }

    @Override
    public void insert(Pricing pricing) {
        String query = "INSERT INTO PRICING VALUES(?,?,?,?,?);";
        jdbcTemplate.update(
                query,
                pricing.getPricingId(),
                pricing.getPricingSchemeNumber(),
                pricing.getDuration(),
                pricing.getGranularity(),
                pricing.getPrice()
        );
    }

    @Override
    public void batchInsert(List<Pricing> pricingList) {
        String query = "INSERT INTO PRICING VALUES(?,?,?,?,?);";
        this.jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, pricingList.get(i).getPricingId());
                preparedStatement.setInt(2, pricingList.get(i).getPricingSchemeNumber());
                preparedStatement.setInt(3, pricingList.get(i).getDuration());
                preparedStatement.setString(4, pricingList.get(i).getGranularity());
                preparedStatement.setDouble(5, pricingList.get(i).getPrice());
            }

            @Override
            public int getBatchSize() {
                return pricingList.size();
            }
        });
    }

    @Override
    public void update(Pricing pricing) {
        String query = "UPDATE PRICING SET Pricing_Scheme_Number=?, Pricing_Duration=?, Pricing_Granularity=?, Pricing_Price=? WHERE Pricing_ID=?";
        jdbcTemplate.update(
                query,
                pricing.getPricingSchemeNumber(),
                pricing.getDuration(),
                pricing.getGranularity(),
                pricing.getPrice(),
                pricing.getPricingId()
        );
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM PRICING WHERE Pricing_ID=?";
        jdbcTemplate.update(query, id);
    }

    public class PricingMapper implements RowMapper<Pricing> {
        public Pricing mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Pricing(
                    rs.getInt("Pricing_ID"),
                    rs.getInt("Pricing_Scheme_Number"),
                    rs.getInt("Pricing_Duration"),
                    rs.getString("Pricing_Granularity"),
                    rs.getDouble("Pricing_Price"));
        }
    }
}
