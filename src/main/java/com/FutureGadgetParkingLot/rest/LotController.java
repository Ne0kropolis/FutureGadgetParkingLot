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
    public LotController(LotService lotService) { this.lotService = lotService; }

    @Path("/all")
    @GET
    @Produces("application/json")
    public List getAllLots() {
        return this.lotService.getAllLots();
    }

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public Lot getLotById(@PathParam("id") int id) {
        return this.lotService.getLotById(id);
    }

    @Path("/create/json")
    @POST
    @Produces("application/json")
    public void createLot(Lot lot) {
        this.lotService.createLot(lot);
    }

    @Path("/create/list/json")
    @POST
    @Produces("application/json")
    public void createLots(List<Lot> lotList) { this.lotService.createLots(lotList); }

    @Path("/update/json")
    @PUT
    @Consumes("application/json")
    public void updateLot(Lot lot) {
        this.lotService.updateLot(lot);
    }

    @Path("/query/capacity")
    @PUT
    public void updateLotCapacity(@QueryParam("id") int id, @QueryParam("capacity")int capacity) {
        this.lotService.updateLotCapacity(id, capacity);
    }

    @Path("/delete/{id}")
    @DELETE
    public void deleteLot(@PathParam("id") int id) { this.lotService.deleteLot(id);}
}
