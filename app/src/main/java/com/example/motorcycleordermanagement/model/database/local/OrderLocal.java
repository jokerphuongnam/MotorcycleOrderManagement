package com.example.motorcycleordermanagement.model.database.local;


import com.example.motorcycleordermanagement.model.database.domain.Order;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface OrderLocal {
    Flowable<List<Order>> getAllData();

    /**
     * for test data
     * */
    List<Order> getAll();

    Completable insert(Order order);

    Completable delete(Order order);

    Completable update(Order order);

    Flowable<List<Order>> filter(long from, long to);
}
