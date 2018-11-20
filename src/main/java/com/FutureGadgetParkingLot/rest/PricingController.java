package com.FutureGadgetParkingLot.rest;

import com.FutureGadgetParkingLot.domain.Pricing;
import com.FutureGadgetParkingLot.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
