package com.FutureGadgetParkingLot.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PricingScheme {
    private List<Pricing> prices;

    private double lostTicketPrice;

    public PricingScheme(List<Pricing> prices) {
        this.prices = prices;
        prices.sort(Pricing.sortByDuration);
    }

    public double getLostTicketPrice() {
        return lostTicketPrice;
    }

    public List<Pricing> getPrices() {return prices;}

    public List<Long> getDurations() {
        List<Long> durations = new ArrayList<Long>();
        durations.add((long) 0);

        for (int i=0; i<prices.size(); i++) {
            switch (prices.get(i).getGranularity().toUpperCase()) {
                case"L":
                    this.setLostTicketPrice(prices.get(i).getPrice());
                    break;
                case"S":
                    durations.add((long) prices.get(i).getPrice()/60);
                    break;
                case"M":
                    durations.add((long) prices.get(i).getPrice());
                    break;
                case"H":
                    durations.add((long) prices.get(i).getPrice()*60);
                    break;
                case"D":
                    durations.add((long) prices.get(i).getPrice()*60*24);
                    break;
            }
        }
        return durations;
    }

    public void setLostTicketPrice(double lostTicketPrice) {
        this.lostTicketPrice = lostTicketPrice;
    }
}
