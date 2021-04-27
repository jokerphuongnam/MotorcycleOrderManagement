package com.example.motorcycleordermanagement.ui.main.motorcycle;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentMotorcycleBinding;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.ui.edit.EditActivity;
import com.example.motorcycleordermanagement.utils.ItemClickRecycler;
import com.example.schoolappliancesmanager.util.Resource;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.motorcycleordermanagement.ui.edit.EditActivity.DATA;
import static com.example.motorcycleordermanagement.ui.edit.EditActivity.TYPE_EDIT;

@AndroidEntryPoint
public class MotorcycleFragment extends BaseFragment<FragmentMotorcycleBinding, MotorcycleViewModel> {
    public MotorcycleFragment() {
        super(R.layout.fragment_motorcycle);
    }

    private MotorcycleAdapter adapter = null;

    @NonNull
    private MotorcycleAdapter getAdapter(){
        if(adapter == null){
            adapter = new MotorcycleAdapter(new ItemClickRecycler<Motorcycle>() {
                @Override
                public void delete(Motorcycle item) {
                    viewModel.delete(item);
                }

                @Override
                public void edit(Motorcycle item) {
                    Intent intent = new Intent(getContext(), EditActivity.class);
                    intent.putExtra(TYPE_EDIT, EditActivity.TypeEdit.MOTORCYCLE.name());
                    intent.putExtra(DATA, item);
                    startActivity(intent);
                }
            });
        }
        return adapter;
    }

    private void setUpRecycle(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.list.setLayoutManager(layoutManager);
        binding.list.setAdapter(getAdapter());
    }

    @Override
    public void createView() {
        setUpRecycle();
        viewModel.getSuccess().observe(getViewLifecycleOwner(), resource -> {
            if (resource instanceof Resource.Success) {
                showToast(getString(R.string.delete_complete));
            }
        });
        viewModel.getData().observe(getViewLifecycleOwner(), (motorcycles) -> {
            getAdapter().submitList(motorcycles);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.initData();
    }

    @Override
    public MotorcycleViewModel getViewModel() {
        return new ViewModelProvider(this).get(MotorcycleViewModel.class);
    }
}
