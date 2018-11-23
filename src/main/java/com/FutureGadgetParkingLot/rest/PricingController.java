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

    @Path("/all")
    @GET
    @Produces("application/json")
    public List getAllPricings() {
        return this.pricingService.getAllPricings();
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Pricing getPricingById(@PathParam("id") int id) {
        return this.pricingService.getPricingById(id);
    }

    @Path("/scheme/{id}")
    @GET
    @Produces("application/json")
    public List<Pricing> getPricingBySchemeId(@PathParam("id") int id) {return this.pricingService.getPricingBySchemeNumber(id);}

    @Path("/create/json")
    @POST
    @Consumes("application/json")
    public void createPricing(Pricing pricing) {
        this.pricingService.createPricing(pricing);
    }

    @Path("/create/list/json")
    @POST
    @Consumes("application/json")
    public void createPricings(List<Pricing> pricingList) {
        this.pricingService.createPricings(pricingList);
    }

    @Path("/update/json")
    @PUT
    @Consumes("application/json")
    public void updatePricing(Pricing pricing) {
        this.pricingService.updatePricing(pricing);
    }

    @Path("/delete/{id}")
    @DELETE
    public void deletePricing(@PathParam("id") int id) {this.pricingService.deletePricing(id);}

}
