package com.FutureGadgetParkingLot.FutureGadgetParkingLot.data;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    void setDataSource(DataSource dataSource);

    Optional get();

    List<T> getAll();

    void insert(T t);

    void update();

    void delete(T t);
}
