package com.example.motorcycleordermanagement.model.usecase;

import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.model.database.domain.Order;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Singleton
public interface AddDetailOrderUseCase {
    Completable insert(DetailOrder detailOrder);

    Completable edit(DetailOrder detailOrder);

    Flowable<List<Order>> getRoomNames();

    Flowable<List<Motorcycle>> getAvailableMotorcycle();
}
