package com.example.motorcycleordermanagement.model.database.local;

import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface MotorcycleLocal {
    Flowable<List<Motorcycle>> getAllData();

    /**
     * for test data
     * */
    List<Motorcycle> getAll();

    Flowable<List<Motorcycle>> getAvailableMotorcycle();

    Completable insert(Motorcycle motorcycle);

    Completable delete(Motorcycle motorcycle);

    Completable update(Motorcycle motorcycle);
}
