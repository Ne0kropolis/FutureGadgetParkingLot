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

/**
 * Service for handling Ticket price calculations.
 * Maintains a cached mapped of Pricing Schemes called during a session.
 */
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

    /**
     * Iterates through a pricing scheme and checks for a suitable duration range. Otherwise, the lost ticket fee for that scheme is charged.
     * @param duration: duration calculated by DateCalculationService (If not Lost).
     * @param lotId: LotId of Ticket
     * @param lost: Boolean of whether or not a ticket has been lost or timed out.
     * @return double price: calculated price of ticket.
     */
    public double calculateTicketPrice(long duration, int lotId, boolean lost) {
        double price = 0;
        PricingScheme pricingScheme = this.checkPricingScheme(lotId);
        List<Pricing> durations = pricingScheme.getDurations();

        if (!lost){
            for (int i=0; i<durations.size()-1; i++) {
                if (duration <= durations.get(i + 1).getDuration() && duration >= durations.get(i).getDuration()) {
                    price = durations.get(i+1).getPrice();
                }
            }
            if (price == 0) {
                price = pricingScheme.getLostTicketPrice();
            }
        } else {
            return pricingScheme.getLostTicketPrice();
        }
        return price;
    }

    /**
     * Get a pricing scheme from the pricing DAO given a lotId
     * @param lotId
     * @return PricingScheme of given lotId
     */
    public int getPricingSchemeNumber(int lotId) {
        return this.lotJdbcDao.getPricingSchemeNumber(lotId);
    }

    /**
     * Checks whether or not a given lot has been called before, if not it is added to the current map of lots and their associated pricing schemes,
     * @param lotId
     * @return PricingScheme of given lotId
     */
    public PricingScheme checkPricingScheme(int lotId) {
        if (!pricingSchemeHashMap.containsKey(lotId)) {
            pricingSchemeHashMap.put(lotId, pricingJdbcDao.getPricingScheme(this.getPricingSchemeNumber(lotId)));
        }
        return pricingSchemeHashMap.get(lotId);
    }



}
