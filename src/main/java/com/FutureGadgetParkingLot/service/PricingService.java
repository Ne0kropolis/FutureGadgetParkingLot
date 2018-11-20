package com.FutureGadgetParkingLot.service;

import com.FutureGadgetParkingLot.data.Pricing_JDBC_DAO;
import com.FutureGadgetParkingLot.domain.Pricing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {
    private final Pricing_JDBC_DAO pricingJdbcDao;

    @Autowired
    public PricingService(@Qualifier("pricing_JDBC_DAO") Pricing_JDBC_DAO pricingJdbcDao) {
        this.pricingJdbcDao = pricingJdbcDao;
    }

    public List getAllPricings() {
        return (pricingJdbcDao.getAll());
    }

    public Pricing getPricingById(int id) {
        return (pricingJdbcDao.get(id));
    }
}
