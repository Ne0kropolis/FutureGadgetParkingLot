package com.FutureGadgetParkingLot.service;

import com.FutureGadgetParkingLot.data.Lot_JDBC_DAO;
import com.FutureGadgetParkingLot.domain.Lot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Lot Service Implementation
 */
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

    public void createLot(Lot lot) { this.lotJdbcDao.insert(lot); }

    public void createLots(List<Lot> lotList) { this.lotJdbcDao.batchInsert(lotList); }

    public void updateLot(Lot lot) { this.lotJdbcDao.update(lot); }

    /**
     * update the capacity of a given lot.
     * @param id: lotId of given Lot
     * @param capcatiy: new capacity value of lot on ticket creation or retrieval.
     */
    public void updateLotCapacity(int id, int capcatiy) { this.lotJdbcDao.updateCapacity(id, capcatiy); }

    public void deleteLot(int id) { this.lotJdbcDao.delete(id); }
}
