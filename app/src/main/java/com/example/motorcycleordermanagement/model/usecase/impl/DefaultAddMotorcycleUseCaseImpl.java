package com.example.motorcycleordermanagement.model.usecase.impl;


import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.model.repository.MotorcycleRepository;
import com.example.motorcycleordermanagement.model.usecase.AddMotorcycleUseCase;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultAddMotorcycleUseCaseImpl implements AddMotorcycleUseCase {

    private final MotorcycleRepository repository;

    @Inject
    public DefaultAddMotorcycleUseCaseImpl(MotorcycleRepository repository) {
        this.repository = repository;
    }

    @Override
    public @NonNull Completable add(Motorcycle motorcycle) {
        return  repository.insert(motorcycle).subscribeOn(Schedulers.io());
    }

    @Override
    public @NonNull Completable edit(Motorcycle motorcycle) {
        return  repository.update(motorcycle).subscribeOn(Schedulers.io());
    }
}
