package com.example.motorcycleordermanagement.model.database.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.model.database.local.MotorcycleLocal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MotorcycleDao extends MotorcycleLocal {
    @Query("SELECT * FROM MOTORCYCLES")
    @Override
    Flowable<List<Motorcycle>> getAllData();

    @Query("SELECT * FROM MOTORCYCLES")
    @Override
    List<Motorcycle> getAll();

    @Query("SELECT * FROM MOTORCYCLES WHERE count > 0")
    @Override
    Flowable<List<Motorcycle>> getAvailableMotorcycle();

    @Insert
    @Override
    Completable insert(Motorcycle motorcycle);

    @Delete
    @Override
    Completable delete(Motorcycle motorcycle);

    @Update
    @Override
    Completable update(Motorcycle motorcycle);
}
