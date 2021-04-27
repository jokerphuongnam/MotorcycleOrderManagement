package com.example.motorcycleordermanagement.model.repository;

import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Singleton
public interface MotorcycleRepository {
    Flowable<List<Motorcycle>> getAllData();

    Flowable<List<Motorcycle>> getAvailableMotorcycle();

    Completable insert(Motorcycle motorcycle);

    Completable delete(Motorcycle motorcycle);

    Completable update(Motorcycle motorcycle);
}
