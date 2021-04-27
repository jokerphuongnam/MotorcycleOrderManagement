package com.example.motorcycleordermanagement.ui.main.detailorder;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentDetailOrderBinding;
import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.ui.edit.EditActivity;
import com.example.motorcycleordermanagement.utils.ItemClickRecycler;
import com.example.schoolappliancesmanager.util.Resource;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.motorcycleordermanagement.ui.edit.EditActivity.DATA;
import static com.example.motorcycleordermanagement.ui.edit.EditActivity.TYPE_EDIT;

@AndroidEntryPoint
public class DetailOrderFragment extends BaseFragment<FragmentDetailOrderBinding, DetailOrderViewModel> {
    public DetailOrderFragment() {
        super(R.layout.fragment_detail_order);
    }

    private DetailOrderAdapter adapter = null;

    @NonNull
    private DetailOrderAdapter getAdapter() {
        if (adapter == null) {
            adapter = new DetailOrderAdapter(new ItemClickRecycler<DetailOrder>() {
                @Override
                public void delete(DetailOrder item) {
                    viewModel.delete(item);
                }

                @Override
                public void edit(DetailOrder item) {
                    Intent intent = new Intent(getContext(), EditActivity.class);
                    intent.putExtra(TYPE_EDIT, EditActivity.TypeEdit.DETAIL_ORDER.name());
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
        viewModel.getData().observe(getViewLifecycleOwner(), (detailOrders) -> {
            getAdapter().submitList(detailOrders);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.initData();
    }

    @Override
    public DetailOrderViewModel getViewModel() {
        return new ViewModelProvider(this).get(DetailOrderViewModel.class);
    }
}
