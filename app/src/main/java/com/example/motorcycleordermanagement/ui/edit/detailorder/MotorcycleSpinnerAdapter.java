package com.example.motorcycleordermanagement.ui.edit.detailorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.motorcycleordermanagement.databinding.ItemMotorbycleSpinnerBinding;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;

import java.util.List;

public class MotorcycleSpinnerAdapter extends ArrayAdapter<Motorcycle> {
    public MotorcycleSpinnerAdapter(@NonNull Context context, List<Motorcycle> applianceList) {
        super(context, 0, applianceList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, parent);
    }

    @NonNull
    private View initView(int position, @NonNull ViewGroup parent){
        ItemMotorbycleSpinnerBinding binding = ItemMotorbycleSpinnerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setMotorcycle(getItem(position));
        return binding.getRoot();
    }
}
