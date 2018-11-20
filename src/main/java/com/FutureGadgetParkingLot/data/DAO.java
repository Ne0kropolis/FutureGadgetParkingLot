package com.FutureGadgetParkingLot.data;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    void setDataSource(DataSource dataSource);

    T get(int id);

    List<T> getAll();

    void insert(T t);

    void update(T t);

    void delete(int id);
}
