package com.example.motorcycleordermanagement.model.database.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.model.database.local.dao.DetailOrderDao;
import com.example.motorcycleordermanagement.model.database.local.dao.MotorcycleDao;
import com.example.motorcycleordermanagement.model.database.local.dao.OrderDao;
import com.example.motorcycleordermanagement.utils.RoomUtils;

import javax.inject.Singleton;


@Singleton
@Database(
        entities = {Motorcycle.class, DetailOrder.class, Order.class},
        version = RoomUtils.DB_VERSION
)
//@TypeConverters(EnumConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MotorcycleDao getMotorcycleDao();
    public abstract DetailOrderDao getDetailOrderDao();
    public abstract OrderDao getOrderDao();
}
