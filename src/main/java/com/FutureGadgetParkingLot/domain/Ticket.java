package com.FutureGadgetParkingLot.domain;

import java.io.Serializable;
import java.sql.Timestamp;

public class Ticket implements Serializable {

    private int ticketId;
    private int lotId;
    private Timestamp timeIn;
    private Timestamp timeOut;
    private double price;
    private boolean lost;

    public Ticket() {}

    public Ticket(int ticketId, int lotId, Timestamp timeIn, Timestamp timeOut, double price, boolean lost) {
        this.ticketId = ticketId;
        this.lotId = lotId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.price = price;
        this.lost = lost;
    }

    public Ticket(int ticketId, int lotId, Timestamp timeIn, Timestamp timeOut, boolean lost) {
        this.ticketId = ticketId;
        this.lotId = lotId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.price = 0;
        this.lost = lost;
    }

    public int getTicketId() { return ticketId; }

    public int getLotId() {
        return lotId;
    }

    public Timestamp getTimeIn() {
        return timeIn;
    }

    public Timestamp getTimeOut() {
        return timeOut;
    }

    public double getPrice() {
        return price;
    }

    public boolean getLost() { return lost; }

}
