package com.example.motorcycleordermanagement.model.repository;


import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Singleton
public interface DetailOrderRepository {
    Flowable<List<DetailOrder>> getAllData();

    Completable insert(DetailOrder detailOrder);

    Completable delete(DetailOrder detailOrder);

    Completable update(DetailOrder detailOrder);
}
