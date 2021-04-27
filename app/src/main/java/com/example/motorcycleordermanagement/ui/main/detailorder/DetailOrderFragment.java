package com.example.motorcycleordermanagement.ui.main.detailorder;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentDetailOrderBinding;
import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.utils.ItemClickRecycler;

import dagger.hilt.android.AndroidEntryPoint;

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
//                    Intent intent = getIntent();
//                    intent.putExtra(DATA, item);
//                    startActivity(intent);
                }
            });
        }
        return adapter;
    }


    @Override
    public void createView() {
        binding.list.setAdapter(getAdapter());
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
