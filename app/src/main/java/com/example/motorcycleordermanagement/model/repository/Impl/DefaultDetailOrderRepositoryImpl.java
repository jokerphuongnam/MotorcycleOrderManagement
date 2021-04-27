package com.example.motorcycleordermanagement.model.repository.Impl;

import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.model.database.local.DetailOrderLocal;
import com.example.motorcycleordermanagement.model.repository.DetailOrderRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DefaultDetailOrderRepositoryImpl implements DetailOrderRepository {
    private final DetailOrderLocal local;

    @Inject
    public DefaultDetailOrderRepositoryImpl(DetailOrderLocal local) {
        this.local = local;
    }

    @Override
    public Flowable<List<DetailOrder>> getAllData() {
        return local.getAllData().subscribeOn(Schedulers.io());
    }

    @Override
    public Completable insert(DetailOrder detailOrder) {
        return local.insert(detailOrder).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable delete(DetailOrder detailOrder) {
        return local.delete(detailOrder).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable update(DetailOrder detailOrder) {
        return local.update(detailOrder).subscribeOn(Schedulers.io());
    }
}
