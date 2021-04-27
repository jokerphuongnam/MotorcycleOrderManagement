package com.example.motorcycleordermanagement.model.usecase;


import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;

import javax.inject.Singleton;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;

@Singleton
public interface AddMotorcycleUseCase {
    @NonNull Completable addAppliance(Motorcycle motorcycle);
    @NonNull Completable editAppliance(Motorcycle motorcycle);
}
