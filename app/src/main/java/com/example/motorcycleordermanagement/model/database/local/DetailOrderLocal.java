package com.example.motorcycleordermanagement.model.database.local;


import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

public interface DetailOrderLocal {
    Flowable<List<DetailOrder>> getAllData();

    Completable insert(DetailOrder detailOrder);

    Completable delete(DetailOrder detailOrder);

    Completable update(DetailOrder detailOrder);
}
