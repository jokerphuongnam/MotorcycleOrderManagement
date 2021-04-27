package com.example.motorcycleordermanagement.model.usecase.impl;

import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.model.repository.DetailOrderRepository;
import com.example.motorcycleordermanagement.model.usecase.DetailOrderUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultDetailOrderUseCaseImpl implements DetailOrderUseCase {

    private final DetailOrderRepository repository;

    @Inject
    public DefaultDetailOrderUseCaseImpl(DetailOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<List<DetailOrder>> getDetailOrder() {
        return repository.getAllData().subscribeOn(Schedulers.io());
    }

    @Override
    public Completable delete(DetailOrder detailOrder) {
        return repository.delete(detailOrder).subscribeOn(Schedulers.io());
    }
}
