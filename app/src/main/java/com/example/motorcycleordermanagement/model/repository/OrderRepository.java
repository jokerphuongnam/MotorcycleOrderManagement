package com.example.motorcycleordermanagement.model.repository;


import com.example.motorcycleordermanagement.model.database.domain.Order;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Singleton
public interface OrderRepository {
    Flowable<List<Order>> getAllData();

    Flowable<List<Order>> filter(long from, long to);

    Completable insert(Order order);

    Completable delete(Order order);

    Completable update(Order order);
}
