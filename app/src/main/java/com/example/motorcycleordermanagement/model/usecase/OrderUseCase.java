package com.example.motorcycleordermanagement.model.usecase;

import com.example.motorcycleordermanagement.model.database.domain.Order;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Singleton
public interface OrderUseCase {
    Flowable<List<Order>> getOrder();

    Flowable<List<Order>> filter(long from, long to);

    Completable delete(Order order);
}
