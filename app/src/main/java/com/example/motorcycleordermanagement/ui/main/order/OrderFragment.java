package com.example.motorcycleordermanagement.ui.main.order;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentOrderBinding;
import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.ui.edit.EditActivity;
import com.example.motorcycleordermanagement.utils.ItemClickRecycler;
import com.example.schoolappliancesmanager.util.Resource;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.motorcycleordermanagement.ui.edit.EditActivity.DATA;
import static com.example.motorcycleordermanagement.ui.edit.EditActivity.TYPE_EDIT;

@AndroidEntryPoint
public class OrderFragment extends BaseFragment<FragmentOrderBinding, OrderViewModel> {

    public OrderFragment() {
        super(R.layout.fragment_order);
    }

    private OrderAdapter adapter = null;

    @NonNull
    private OrderAdapter getAdapter() {
        if (adapter == null) {
            adapter = new OrderAdapter(new ItemClickRecycler<Order>() {
                @Override
                public void delete(Order item) {
                    viewModel.delete(item);
                }

                @Override
                public void edit(Order item) {
                    Intent intent = new Intent(getContext(), EditActivity.class);
                    intent.putExtra(TYPE_EDIT, EditActivity.TypeEdit.ORDER.name());
                    intent.putExtra(DATA, item);
                    startActivity(intent);
                }
            });
        }
        return adapter;
    }

    private void setUpRecycle() {
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
        viewModel.getData().observe(getViewLifecycleOwner(), (orders) -> {
            getAdapter().submitList(orders);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.initData();
    }

    @Override
    public OrderViewModel getViewModel() {
        return new ViewModelProvider(this).get(OrderViewModel.class);
    }
}
