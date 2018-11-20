package com.FutureGadgetParkingLot.rest;

import com.FutureGadgetParkingLot.domain.Lot;
import com.FutureGadgetParkingLot.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
}
