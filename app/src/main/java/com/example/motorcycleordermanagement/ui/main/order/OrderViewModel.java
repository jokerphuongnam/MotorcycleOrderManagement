package com.example.motorcycleordermanagement.ui.main.order;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.model.usecase.OrderUseCase;
import com.example.schoolappliancesmanager.util.Resource;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class OrderViewModel extends ViewModel {
    private final OrderUseCase useCase;

    @Inject
    public OrderViewModel(OrderUseCase useCase) {
        this.useCase = useCase;
    }

    private MutableLiveData<List<Order>> data = null;

    public MutableLiveData<List<Order>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }

    private Disposable disposable;

    @Inject
    Gson gson;

    public void initData() {
        disposable = useCase.getOrder().subscribe((orders) -> {
            String json = gson.toJson(orders);
            data.postValue(gson.fromJson(json, new TypeToken<List<Order>>() {
            }.getType()));
        }, Throwable::printStackTrace);
    }

    private MutableLiveData<Resource<Boolean>> success = null;

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

    public void delete(Order order){
        useCase.delete(order).subscribe(getCompletableObserver());
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
