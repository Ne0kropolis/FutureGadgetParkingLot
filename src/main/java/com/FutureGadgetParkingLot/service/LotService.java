package com.FutureGadgetParkingLot.service;

import com.FutureGadgetParkingLot.data.Lot_JDBC_DAO;
import com.FutureGadgetParkingLot.domain.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotService {

    private final Lot_JDBC_DAO lotJdbcDao;

    @Autowired
    public LotService(@Qualifier("lot_JDBC_DAO") Lot_JDBC_DAO lotJdbcDao) {
        this.lotJdbcDao = lotJdbcDao;
    }

    public List getAllLots() {
        return this.lotJdbcDao.getAll();
    }

    public Lot getLotById(int id) {
        return this.lotJdbcDao.get(id);
    }

    public void createLot(Lot lot) {this.lotJdbcDao.insert(lot);}
}
