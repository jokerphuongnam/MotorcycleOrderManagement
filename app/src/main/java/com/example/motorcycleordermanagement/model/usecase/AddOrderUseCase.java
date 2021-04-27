package com.example.motorcycleordermanagement.model.usecase;


import com.example.motorcycleordermanagement.model.database.domain.Order;

import javax.inject.Singleton;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Singleton
public interface AddOrderUseCase {
    @NonNull Completable add(Order order);
    @NonNull Completable edit(Order order);
}
