package com.example.motorcycleordermanagement.model.usecase;

import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Singleton
public interface DetailOrderUseCase {
    Flowable<List<DetailOrder>> getDetailOrder();

    Completable delete(DetailOrder detailOrder);
}
