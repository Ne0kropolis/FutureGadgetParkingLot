package com.FutureGadgetParkingLot.rest;

import com.FutureGadgetParkingLot.domain.Lot;
import com.FutureGadgetParkingLot.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.util.List;

@Controller
@Path("/lot")
public class LotController {


    private final LotService lotService;

    @Autowired
    public LotController(LotService lotService) {this.lotService = lotService;}

    @Path("/get/all")
    @GET
    @Produces("application/json")
    public List getAllLots() {
        return lotService.getAllLots();
    }

    @Path("/get/{id}")
    @GET
    @Produces("application/json")
    public Lot getLotById(@PathParam("id") int id) {
        return lotService.getLotById(id);
    }

    @Path("/create/json")
    @POST
    @Produces("application/json")
    public void createLot(Lot lot) {
        this.lotService.createLot(lot);
    }
}
