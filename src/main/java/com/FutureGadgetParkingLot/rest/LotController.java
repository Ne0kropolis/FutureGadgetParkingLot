package com.FutureGadgetParkingLot.rest;

import com.FutureGadgetParkingLot.domain.Lot;
import com.FutureGadgetParkingLot.service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import java.util.List;

@RestController
@RequestMapping("/lot")
public class LotController {


    private final LotService lotService;

    @Autowired
    public LotController(LotService lotService) {this.lotService = lotService;}

    @GetMapping("/get/all")
    @Produces("application/json")
    public List getAllLots() {
        return lotService.getAllLots();
    }

    @GetMapping("get/{id}")
    @Produces("application/json")
    public Lot getLotById(@PathVariable int id) {
        return lotService.getLotById(id);
    }
}
