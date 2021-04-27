package com.example.motorcycleordermanagement.model.usecase.impl;

import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.model.repository.OrderRepository;
import com.example.motorcycleordermanagement.model.usecase.OrderUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultOrderUseCaseImpl implements OrderUseCase {

    private final OrderRepository repository;

    @Inject
    public DefaultOrderUseCaseImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<List<Order>> getOrder() {
        return repository.getAllData().subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<Order>> filter(long from, long to) {
        return repository.filter(from, to).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable delete(Order order) {
        return repository.delete(order).subscribeOn(Schedulers.io());
    }
}
