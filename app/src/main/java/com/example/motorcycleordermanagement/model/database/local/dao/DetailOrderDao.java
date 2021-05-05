package com.example.motorcycleordermanagement.model.database.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.model.database.local.DetailOrderLocal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface DetailOrderDao extends DetailOrderLocal {
    @Query("SELECT * FROM DETAIL_ORDER")
    @Override
    Flowable<List<DetailOrder>> getAllData();

    @Insert
    @Override
    Completable insert(DetailOrder detailOrder);

    @Delete
    @Override
    Completable delete(DetailOrder detailOrder);

    @Delete
    @Override
    Completable update(DetailOrder detailOrder);
}
