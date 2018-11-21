package com.FutureGadgetParkingLot.domain;

import java.util.List;

public class PricingScheme {
    private List<Pricing> prices;

    private double lostTicketPrice;

    public PricingScheme(List<Pricing> prices) {
        this.prices = prices;
    }

    public double getLostTicketPrice() {
        return lostTicketPrice;
    }

    public List<Pricing> getPrices() {
        return this.prices;
    }

    public void setLostTicketPrice(double lostTicketPrice) {
        this.lostTicketPrice = lostTicketPrice;
    }
}
