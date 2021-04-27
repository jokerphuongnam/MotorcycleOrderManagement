package com.example.motorcycleordermanagement.model.usecase.impl;

import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.model.repository.MotorcycleRepository;
import com.example.motorcycleordermanagement.model.usecase.MotorcycleUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultMotorcycleUseCaseImpl implements MotorcycleUseCase {

    private final MotorcycleRepository repository;

    @Inject
    public DefaultMotorcycleUseCaseImpl(MotorcycleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flowable<List<Motorcycle>> getMotorcycle() {
        return repository.getAllData().subscribeOn(Schedulers.io());
    }

    @Override
    public Completable delete(Motorcycle motorcycle) {
        return repository.delete(motorcycle).subscribeOn(Schedulers.io());
    }
}
