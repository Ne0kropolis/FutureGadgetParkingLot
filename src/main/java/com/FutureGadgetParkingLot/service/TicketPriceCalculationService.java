package com.FutureGadgetParkingLot.service;

import com.FutureGadgetParkingLot.data.Lot_JDBC_DAO;
import com.FutureGadgetParkingLot.data.Pricing_JDBC_DAO;
import com.FutureGadgetParkingLot.domain.Pricing;
import com.FutureGadgetParkingLot.domain.PricingScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TicketPriceCalculationService {

    private Lot_JDBC_DAO lotJdbcDao;

    private Pricing_JDBC_DAO pricingJdbcDao;

    private HashMap<Integer, PricingScheme> pricingSchemeHashMap = new HashMap<>();

    public TicketPriceCalculationService() {}

    @Autowired
    public void setLotJdbcDao(Lot_JDBC_DAO lotJdbcDao) {
        this.lotJdbcDao = lotJdbcDao;
    }

    @Autowired
    public void setPricing_jdbc_dao(Pricing_JDBC_DAO pricingJdbcDao) {
        this.pricingJdbcDao = pricingJdbcDao;
    }

    public double calculateTicketPrice(long duration, int lotId, boolean lost) {
        double price = 0;
        PricingScheme pricingScheme = this.checkPricingScheme(lotId);
        List<Pricing> prices = pricingScheme.getPrices();

        if (!lost){
            for (int i=0; i<prices.size()-1; i++) {
                if (duration <= prices.get(i + 1).getDuration() && duration >= prices.get(i).getDuration()) {
                    price = prices.get(i + 1).getPrice();
                }
            }
        } else {
            return pricingScheme.getLostTicketPrice();
        }
        return price;
    }

    public int getPricingSchemeNumber(int lotId) {
        return this.lotJdbcDao.getPricingSchemeNumber(lotId);
    }

    public PricingScheme checkPricingScheme(int lotId) {
        if (!pricingSchemeHashMap.containsKey(lotId)) {
            pricingSchemeHashMap.put(lotId, pricingJdbcDao.getPricingScheme(this.getPricingSchemeNumber(lotId)));
        }
        return pricingSchemeHashMap.get(lotId);
    }



}
