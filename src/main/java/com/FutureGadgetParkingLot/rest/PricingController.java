package com.FutureGadgetParkingLot.rest;

import com.FutureGadgetParkingLot.domain.Pricing;
import com.FutureGadgetParkingLot.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.util.List;

@Controller
@Path("/pricing")
public class PricingController {

    private final PricingService pricingService;

    @Autowired
    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Path("/get/all")
    @GET
    @Produces("application/json")
    public List getAllPricings() {
        return this.pricingService.getAllPricings();
    }

    @Path("/get/{id}")
    @GET
    @Produces("application/json")
    public Pricing getPricingById(@PathParam("id") int id) {
        return this.pricingService.getPricingById(id);
    }

    @Path("/create/json")
    @POST
    @Consumes("application/json")
    public void createPricing(Pricing pricing) {
        this.pricingService.createPricing(pricing);
    }
}
