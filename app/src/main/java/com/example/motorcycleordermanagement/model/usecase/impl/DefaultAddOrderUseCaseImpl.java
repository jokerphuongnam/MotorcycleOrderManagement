package com.example.motorcycleordermanagement.model.usecase.impl;

import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.model.repository.OrderRepository;
import com.example.motorcycleordermanagement.model.usecase.AddOrderUseCase;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultAddOrderUseCaseImpl implements AddOrderUseCase {

    private final OrderRepository repository;

    @Inject
    public DefaultAddOrderUseCaseImpl(OrderRepository repository) {
        this.repository = repository;
    }


    @Override
    public @NonNull Completable addRoom(Order order) {
        return repository.insert(order).subscribeOn(Schedulers.io());
    }

    @Override
    public @NonNull Completable editRoom(Order order) {
        return repository.update(order).subscribeOn(Schedulers.io());
    }
}
