package com.FutureGadgetParkingLot.domain;

import java.io.Serializable;
import java.util.List;

public class Ticket implements Serializable {

    private int ticketId;
    private int lotId;
    private String date;
    private String timeIn;
    private String timeOut;
    private double price;
    private boolean lost;

    public Ticket() {}

    public Ticket(int ticketId, int lotId, String date, String timeIn, String timeOut, double price, boolean lost) {
        this.ticketId = ticketId;
        this.lotId = lotId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.price = price;
        this.lost = lost;
    }

    public Ticket(int ticketId, int lotId, String date, String timeIn, String timeOut, boolean lost) {
        this.ticketId = ticketId;
        this.lotId = lotId;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.price = 0;
        this.lost = lost;
    }

    public int getTicketId() { return ticketId; }

    public int getLotId() {
        return lotId;
    }

    public String getDate() {
        return date;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public double getPrice() {
        return price;
    }

    public boolean getLost() { return lost; }

}
