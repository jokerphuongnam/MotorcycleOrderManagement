package com.example.motorcycleordermanagement.di;


import androidx.annotation.NonNull;

import com.example.motorcycleordermanagement.model.repository.Impl.DefaultDetailOrderRepositoryImpl;
import com.example.motorcycleordermanagement.model.repository.MotorcycleRepository;
import com.example.motorcycleordermanagement.model.repository.Impl.DefaultMotorcycleRepositoryImpl;
import com.example.motorcycleordermanagement.model.repository.Impl.DefaultOrderRepositoryImpl;
import com.example.motorcycleordermanagement.model.repository.DetailOrderRepository;
import com.example.motorcycleordermanagement.model.repository.OrderRepository;
import com.example.motorcycleordermanagement.model.usecase.AddMotorcycleUseCase;
import com.example.motorcycleordermanagement.model.usecase.AddOrderUseCase;
import com.example.motorcycleordermanagement.model.usecase.DetailOrderUseCase;
import com.example.motorcycleordermanagement.model.usecase.MotorcycleUseCase;
import com.example.motorcycleordermanagement.model.usecase.AddDetailOrderUseCase;
import com.example.motorcycleordermanagement.model.usecase.impl.DefaultAddDetailOrderUseCaseImpl;
import com.example.motorcycleordermanagement.model.usecase.impl.DefaultAddMotorcycleUseCaseImpl;
import com.example.motorcycleordermanagement.model.usecase.impl.DefaultAddOrderUseCaseImpl;
import com.example.motorcycleordermanagement.model.usecase.impl.DefaultMotorcycleUseCaseImpl;
import com.example.motorcycleordermanagement.model.usecase.impl.DefaultDetailOrderUseCaseImpl;
import com.example.motorcycleordermanagement.model.usecase.impl.DefaultOrderUseCaseImpl;
import com.example.motorcycleordermanagement.model.usecase.OrderUseCase;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppBindsModules {
    @Binds
    @NonNull
    abstract MotorcycleRepository getApplianceRepository(DefaultMotorcycleRepositoryImpl repository);
    @Binds
    @NonNull
    abstract DetailOrderRepository getDetailUsedRepository(DefaultDetailOrderRepositoryImpl repository);
    @Binds
    @NonNull
    abstract OrderRepository getRoomRepository(DefaultOrderRepositoryImpl repository);
    @Binds
    @NonNull
    abstract AddOrderUseCase getAddRoomUseCase(DefaultAddOrderUseCaseImpl useCase);
    @Binds
    @NonNull
    abstract AddMotorcycleUseCase getAddApplianceUseCase(DefaultAddMotorcycleUseCaseImpl useCase);
    @Binds
    @NonNull
    abstract AddDetailOrderUseCase getBorrowUseCase(DefaultAddDetailOrderUseCaseImpl useCase);
    @Binds
    @NonNull
    abstract MotorcycleUseCase getAppliancesUseCase(DefaultMotorcycleUseCaseImpl useCase);
    @Binds
    @NonNull
    abstract DetailOrderUseCase getDetailUsedUseCase(DefaultDetailOrderUseCaseImpl useCase);
    @Binds
    @NonNull
    abstract OrderUseCase getRoomsUseCase(DefaultOrderUseCaseImpl useCase);
}
