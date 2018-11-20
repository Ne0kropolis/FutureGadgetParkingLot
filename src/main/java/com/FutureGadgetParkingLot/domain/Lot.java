package com.FutureGadgetParkingLot.domain;

import java.io.Serializable;

public class Lot implements Serializable {
    private int lotId;
    private int pricingSchemeNumber;
    private String lotName;
    private String lotAddress;
    private int lotCapactiy;

    public Lot() {}

    public Lot(int lotId, int pricingSchemeNumber, String lotName, String lotAddress, int lotCapactiy) {
        this.lotId = lotId;
        this.pricingSchemeNumber = pricingSchemeNumber;
        this.lotName = lotName;
        this.lotAddress = lotAddress;
        this.lotCapactiy = lotCapactiy;
    }

    public int getLotId() {
        return lotId;
    }

    public int getPricingSchemeNumber() {
        return pricingSchemeNumber;
    }

    public String getLotName() {
        return lotName;
    }

    public String getLotAddress() {
        return lotAddress;
    }

    public int getLotCapactiy() {
        return lotCapactiy;
    }
}
