package com.FutureGadgetParkingLot.data;

import javax.sql.DataSource;
import java.util.List;

public interface DAO<T> {

    void setDataSource(DataSource dataSource);

    T get(int id);

    List<T> getAll();

    void insert(T t);

    void batchInsert(List<T> t);

    void update(T t);

    void delete(int id);
}
