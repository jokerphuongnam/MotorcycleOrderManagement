package com.example.motorcycleordermanagement.ui.main.detailorder;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.model.usecase.DetailOrderUseCase;
import com.example.motorcycleordermanagement.utils.ObjectUtil;
import com.example.schoolappliancesmanager.util.Resource;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class DetailOrderViewModel extends ViewModel {
    private final DetailOrderUseCase useCase;

    @Inject
    public DetailOrderViewModel(DetailOrderUseCase useCase) {
        this.useCase = useCase;
    }

    private MutableLiveData<List<DetailOrder>> data = null;

    public MutableLiveData<List<DetailOrder>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
        }
        return data;
    }

    private Disposable disposable;

    @Inject
    Gson gson;

    public void initData() {
        disposable = useCase.getDetailOrder().subscribe((orders) -> {
            data.postValue(ObjectUtil.clone(orders, gson));
        }, Throwable::printStackTrace);
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

    public void delete(DetailOrder detailOrder) {
        useCase.delete(detailOrder).subscribe(getCompletableObserver());
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }

}
