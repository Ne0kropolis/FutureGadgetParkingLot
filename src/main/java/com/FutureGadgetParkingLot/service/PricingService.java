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
        return (this.pricingJdbcDao.getAll());
    }

    public Pricing getPricingById(int id) {
        return (this.pricingJdbcDao.get(id));
    }

    public void createPricing(Pricing pricing) {
        this.pricingJdbcDao.insert(pricing);
    }

    public void updatePricing(Pricing pricing) { this.pricingJdbcDao.update(pricing);}

    public void deletePricing(int id) { this.pricingJdbcDao.delete(id);}
}
