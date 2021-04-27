package com.example.motorcycleordermanagement.ui.edit;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class EditViewModel extends ViewModel {

    @Inject
    public EditViewModel() {
    }

    private EditActivity.TypeEdit typeEdit;
    private EditActivity.TypeAction typeAction;

    public EditActivity.TypeAction getTypeAction() {
        return typeAction;
    }

    public void setTypeAction(EditActivity.TypeAction typeAction) {
        this.typeAction = typeAction;
    }

    public EditActivity.TypeEdit getTypeEdit() {
        return typeEdit;
    }

    public void setTypeEdit(EditActivity.TypeEdit typeEdit) {
        this.typeEdit = typeEdit;
    }
}
