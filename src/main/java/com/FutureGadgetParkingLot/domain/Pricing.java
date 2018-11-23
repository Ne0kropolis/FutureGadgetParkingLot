package com.FutureGadgetParkingLot.domain;
/**
 * Domain object for Pricing.
 */

import java.io.Serializable;
import java.util.Comparator;

public class Pricing implements Serializable {
    private int pricingId;
    private int pricingSchemeNumber;
    private int duration;
    private String granularity;
    private double price;

    public Pricing(){ }

    public Pricing(int pricingId, int pricingSchemeNumber, int duration, String granularity, double price) {
        this.pricingId = pricingId;
        this.pricingSchemeNumber = pricingSchemeNumber;
        this.duration = duration;
        this.granularity = granularity;
        this.price = price;
    }

    public int getPricingId() {
        return pricingId;
    }

    public int getPricingSchemeNumber() {
        return pricingSchemeNumber;
    }

    public String getGranularity() {
        return granularity;
    }

    public int getDuration() {
        return this.duration;
    }

    public double getPrice() {
        return this.price;
    }

    public void setDuration(int duration) { this.duration = duration;}

    /**
     * Comparator for sorting durations in ascending order.
     */
    public static Comparator<Pricing> sortByDuration = (o1, o2) -> (Integer.compare(o1.duration, o2.duration));


}
