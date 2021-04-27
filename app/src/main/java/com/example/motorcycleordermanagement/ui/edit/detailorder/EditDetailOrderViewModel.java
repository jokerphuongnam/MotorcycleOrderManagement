package com.example.motorcycleordermanagement.ui.edit.detailorder;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.model.usecase.AddDetailOrderUseCase;
import com.example.schoolappliancesmanager.util.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class EditDetailOrderViewModel extends ViewModel {
    private AddDetailOrderUseCase useCase;

    @Inject
    public EditDetailOrderViewModel(AddDetailOrderUseCase useCase) {
        this.useCase = useCase;
    }

    public DetailOrder getDetailOrder() {
        return detailOrder;
    }

    public void setDetailOrder(DetailOrder detailOrder) {
        this.detailOrder = detailOrder;
    }

    private DetailOrder detailOrder;

    public void initDetailOrder(DetailOrder detailOrder) {
        if (detailOrder == null) {
            this.detailOrder = new DetailOrder();
        } else {
            this.detailOrder = detailOrder;
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

    public void addDetailOrder(){
        useCase.insert(getDetailOrder()).subscribe(getCompletableObserver());
    }
    public void editDetailOrder(){
        useCase.edit(getDetailOrder()).subscribe(getCompletableObserver());
    }
}
