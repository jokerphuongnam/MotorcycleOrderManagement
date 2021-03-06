package com.example.motorcycleordermanagement.model.usecase.impl;

import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.model.repository.DetailOrderRepository;
import com.example.motorcycleordermanagement.model.repository.MotorcycleRepository;
import com.example.motorcycleordermanagement.model.repository.OrderRepository;
import com.example.motorcycleordermanagement.model.usecase.AddDetailOrderUseCase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultAddDetailOrderUseCaseImpl implements AddDetailOrderUseCase {

    private final MotorcycleRepository motorcycleRepository;
    private final DetailOrderRepository detailOrderRepository;
    private final OrderRepository orderRepository;

    @Inject
    public DefaultAddDetailOrderUseCaseImpl(MotorcycleRepository motorcycleRepository, DetailOrderRepository detailOrderRepository, OrderRepository orderRepository) {
        this.motorcycleRepository = motorcycleRepository;
        this.detailOrderRepository = detailOrderRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Completable insert(DetailOrder detailOrder) {
        return detailOrderRepository.insert(detailOrder).andThen(minusCountInMotorcycle(detailOrder)).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable edit(DetailOrder detailOrder) {
        return detailOrderRepository.update(detailOrder).andThen(minusCountInMotorcycle(detailOrder)).subscribeOn(Schedulers.io());
    }

    @NotNull
    private Completable minusCountInMotorcycle(@NotNull DetailOrder detailOrder) {
        return motorcycleRepository.getMotorcycleById(detailOrder.getMotorcycleId()).flatMapCompletable(motorcycle -> {
            int count = motorcycle.getCount();
            motorcycle.setCount(count - detailOrder.getCount());
            return motorcycleRepository.update(motorcycle);
        });
    }

    @Override
    public Flowable<List<Order>> getOrder() {
        return orderRepository.getAllData().subscribeOn(Schedulers.io());
    }

    @Override
    public Flowable<List<Motorcycle>> getAvailableMotorcycles() {
        return motorcycleRepository.getAvailableMotorcycle().subscribeOn(Schedulers.io());
    }
}
