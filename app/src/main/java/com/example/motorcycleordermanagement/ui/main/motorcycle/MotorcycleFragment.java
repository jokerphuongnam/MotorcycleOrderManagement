package com.example.motorcycleordermanagement.ui.main.motorcycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentMotorcycleBinding;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.utils.ItemClickRecycler;

import dagger.hilt.android.AndroidEntryPoint;

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
    public MotorcycleViewModel getViewModel() {
        return new ViewModelProvider(this).get(MotorcycleViewModel.class);
    }
}
