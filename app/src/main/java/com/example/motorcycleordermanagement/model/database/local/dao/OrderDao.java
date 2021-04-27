package com.example.motorcycleordermanagement.model.database.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.model.database.local.OrderLocal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface OrderDao extends OrderLocal {
    @Query("SELECT * FROM ORDERS")
    @Override
    Flowable<List<Order>> getAllData();

    @Query("SELECT * FROM ORDERS")
    @Override
    List<Order> getAll();

    @Insert
    @Override
    Completable insert(Order order);

    @Delete
    @Override
    Completable delete(Order order);

    @Update
    @Override
    Completable update(Order order);


    @Query("SELECT * FROM ORDERS WHERE order_id BETWEEN :from AND :to")
    @Override
    Flowable<List<Order>> filter(long from, long to);
}
