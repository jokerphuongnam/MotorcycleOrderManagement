package com.example.motorcycleordermanagement.ui.main.order;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentOrderBinding;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.ui.main.MainViewModel;
import com.example.motorcycleordermanagement.ui.main.motorcycle.MotorcycleAdapter;
import com.example.motorcycleordermanagement.utils.ItemClickRecycler;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderFragment extends BaseFragment<FragmentOrderBinding, OrderViewModel> {

    public OrderFragment() {
        super(R.layout.fragment_order);
    }

    private OrderAdapter adapter = null;

    @NonNull
    private OrderAdapter getAdapter(){
        if(adapter == null){
            adapter = new OrderAdapter(new ItemClickRecycler<Order>() {
                @Override
                public void delete(Order item) {
                    viewModel.delete(item);
                }

                @Override
                public void edit(Order item) {
//                    Intent intent = getIntent();
//                    intent.putExtra(DATA, item);
//                    startActivity(intent);
                }
            });
        }
        return adapter;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.initData();
    }

    @Override
    public void createView() {
        binding.list.setAdapter(getAdapter());
    }


    @Override
    public OrderViewModel getViewModel() {
        return null;
    }
}
