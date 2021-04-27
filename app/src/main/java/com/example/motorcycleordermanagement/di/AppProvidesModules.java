package com.example.motorcycleordermanagement.di;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.example.motorcycleordermanagement.model.database.local.AppDatabase;
import com.example.motorcycleordermanagement.model.database.local.DetailOrderLocal;
import com.example.motorcycleordermanagement.model.database.local.MotorcycleLocal;
import com.example.motorcycleordermanagement.model.database.local.OrderLocal;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

import static com.example.motorcycleordermanagement.utils.RoomUtils.DB_NAME;


@Module
@InstallIn(SingletonComponent.class)
public class AppProvidesModules {
    @Provides
    @Singleton
    @NonNull
    AppDatabase providerDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
    }

    @Provides
    @Singleton
    @NonNull
    MotorcycleLocal providerMotorcycleLocal(@NotNull AppDatabase appDatabase) {
        return appDatabase.getMotorcycleDao();
    }

    @Provides
    @Singleton
    @NonNull
    DetailOrderLocal providerDetailOrderLocal(@NotNull AppDatabase appDatabase) {
        return appDatabase.getDetailOrderDao();
    }

    @Provides
    @Singleton
    @NonNull
    OrderLocal providerOrderLocal(@NotNull AppDatabase appDatabase) {
        return appDatabase.getOrderDao();
    }

    @Provides
    @Singleton
    @NonNull
    Gson providerGson(){
        return new Gson();
    }
}
