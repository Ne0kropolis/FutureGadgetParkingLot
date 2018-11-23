package com.FutureGadgetParkingLot.domain;

import java.util.Comparator;
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

    public List<Pricing> getDurations() {

        for (int i=0; i<prices.size(); i++) {
            switch (prices.get(i).getGranularity().toUpperCase()) {
                case"L":
                    this.setLostTicketPrice(prices.get(i).getPrice());
                    break;
                case"S":
                    prices.get(i).setDuration(prices.get(i).getDuration()/60);
                    break;
                case"M":
                    prices.get(i).setDuration(prices.get(i).getDuration());
                    break;
                case"H":
                    prices.get(i).setDuration(prices.get(i).getDuration()*60);
                    break;
                case"D":
                    prices.get(i).setDuration(prices.get(i).getDuration()*60*24);
                    break;
            }
        }
        prices.sort(Pricing.sortByDuration);
        return  prices;
    }

    public void setLostTicketPrice(double lostTicketPrice) {
        this.lostTicketPrice = lostTicketPrice;
    }

    public static Comparator<Long> sortByDuration = (o1, o2) -> (Long.compare(o1, o2));
}
