package com.example.motorcycleordermanagement.model.database.local;

import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public interface MotorcycleLocal {
    Flowable<List<Motorcycle>> getAllData();

    /**
     * for test data
     * */
    List<Motorcycle> getAll();

    Flowable<List<Motorcycle>> getAvailableMotorcycle();

    Maybe<Motorcycle> getMotorcycleById(long motorcycleId);

    Completable insert(Motorcycle motorcycle);

    Completable delete(Motorcycle motorcycle);

    Completable update(Motorcycle motorcycle);
}
