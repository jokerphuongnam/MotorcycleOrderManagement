package com.example.motorcycleordermanagement.model.repository.Impl;

import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.model.database.local.OrderLocal;
import com.example.motorcycleordermanagement.model.repository.OrderRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultOrderRepositoryImpl implements OrderRepository {
    private final OrderLocal local;

    @Inject
    public DefaultOrderRepositoryImpl(OrderLocal local) {
        this.local = local;
    }

    @Override
    public Flowable<List<Order>> getAllData() {
        return local.getAllData().subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<Order>> filter(long from, long to) {
        return local.filter(from, to).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable insert(Order order) {
        return local.insert(order).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable delete(Order order) {
        return local.delete(order).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable update(Order order) {
        return local.update(order).subscribeOn(Schedulers.io());
    }
}
