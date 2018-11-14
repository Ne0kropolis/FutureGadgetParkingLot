package com.FutureGadgetParkingLot.FutureGadgetParkingLot.domain;

import java.util.List;

public class PricingScheme {
    private List<Pricing> prices;

    public PricingScheme() {}

    public PricingScheme(List<Pricing> prices) {
        this.prices = prices;
    }

    List<Pricing> getPrices() {
        return this.prices;
    }
}
