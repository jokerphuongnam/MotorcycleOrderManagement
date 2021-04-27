package com.example.motorcycleordermanagement.ui.edit.motorcycle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.model.usecase.AddMotorcycleUseCase;
import com.example.schoolappliancesmanager.util.Resource;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class EditMotorcycleViewModel extends ViewModel {


    private AddMotorcycleUseCase useCase;

    @Inject
    public EditMotorcycleViewModel(AddMotorcycleUseCase useCase) {
        this.useCase = useCase;
    }
    public Motorcycle getMotorcycle() {
        return motorcycle;
    }

    public void setMotorcycle(Motorcycle motorcycle) {
        this.motorcycle = motorcycle;
    }

    private Motorcycle motorcycle;

    public void initMotorcycle(Motorcycle motorcycle) {
        if (motorcycle == null) {
            this.motorcycle = new Motorcycle();
        } else {
            this.motorcycle = motorcycle;
        }
    }

    private MutableLiveData<Resource<Boolean>> success;

    public MutableLiveData<Resource<Boolean>> getSuccess() {
        if (success == null) {
            success = new MutableLiveData<>();
        }
        return success;
    }

    private CompletableObserver completableObserver;

    private CompletableObserver getCompletableObserver() {
        if (completableObserver == null) {
            completableObserver = new CompletableObserver() {
                private Disposable disposable;

                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    disposable = d;
                    getSuccess().postValue(new Resource.Loading<>(true));
                }

                @Override
                public void onComplete() {
                    disposable.dispose();
                    getSuccess().postValue(new Resource.Success<>(true));
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    disposable.dispose();
                    e.printStackTrace();
                    getSuccess().postValue(new Resource.Error<>(""));
                }
            };
        }
        return completableObserver;
    }


    public void addMotocycle(){
        useCase.add(getMotorcycle()).subscribe(getCompletableObserver());
    }
    public void editMotocycle(){
        useCase.edit(getMotorcycle()).subscribe(getCompletableObserver());
    }
}
