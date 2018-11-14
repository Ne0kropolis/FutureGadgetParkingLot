package com.FutureGadgetParkingLot.FutureGadgetParkingLot.domain;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Ticket implements Serializable {
    private int ticketId;
    private int lotId;
    private String date;
    private String timeIn;
    private String timeOut;
    private double price;

    public Ticket() {}

    public Ticket(int ticketId, int lotId, String date, String timeIn, String timeOut, double price) {
        this.ticketId = ticketId;
        this.lotId = lotId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.price = price;
    }

    public Ticket(int ticketId, int lotId, String date, String timeIn, String timeOut) {
        this.ticketId = ticketId;
        this.lotId = lotId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.price = 0;
    }

    public double getPrice() {
        return this.price;
    }

    public void calculatePrice(int duration, PricingScheme pricingScheme) {
        List<Pricing> prices = pricingScheme.getPrices();
        for (int i=0; i<prices.size()-1; i++) {
            if (duration <= prices.get(i+1).getDuration() && duration >= prices.get(i).getDuration()) {
                this.price = prices.get(i+1).getPrice();
            }
        }
    }
}
