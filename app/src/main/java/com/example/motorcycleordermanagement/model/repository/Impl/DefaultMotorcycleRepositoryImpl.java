package com.example.motorcycleordermanagement.model.repository.Impl;

import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.model.database.local.MotorcycleLocal;
import com.example.motorcycleordermanagement.model.repository.MotorcycleRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultMotorcycleRepositoryImpl implements MotorcycleRepository {
    private final MotorcycleLocal local;

    @Inject
    public DefaultMotorcycleRepositoryImpl(MotorcycleLocal local) {
        this.local = local;
    }

    @Override
    public Flowable<List<Motorcycle>> getAllData() {
        return local.getAllData().subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<Motorcycle>> getAvailableMotorcycle() {
        return local.getAvailableMotorcycle();
    }

    @Override
    public Maybe<Motorcycle> getMotorcycleById(long motorcycleId) {
        return local.getMotorcycleById(motorcycleId);
    }

    @Override
    public Completable insert(Motorcycle motorcycle) {
        return local.insert(motorcycle).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable delete(Motorcycle motorcycle) {
        return local.delete(motorcycle).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable update(Motorcycle motorcycle) {
        return local.update(motorcycle).subscribeOn(Schedulers.io());
    }
}
