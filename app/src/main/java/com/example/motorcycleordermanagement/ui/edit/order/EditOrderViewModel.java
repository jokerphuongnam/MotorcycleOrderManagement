package com.example.motorcycleordermanagement.ui.edit.order;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.model.usecase.AddOrderUseCase;
import com.example.schoolappliancesmanager.util.Resource;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class EditOrderViewModel extends ViewModel {

    private AddOrderUseCase useCase;

    @Inject
    public EditOrderViewModel(AddOrderUseCase useCase) {
        this.useCase = useCase;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    private Order order;

    public void initOrder(Order order) {
        if (order == null) {
            this.order = new Order();
        } else {
            this.order = order;
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


    public void addOrder(){
        useCase.add(getOrder()).subscribe(getCompletableObserver());
    }
    public void editOrder(){
        useCase.edit(getOrder()).subscribe(getCompletableObserver());
    }
}
