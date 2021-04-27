package com.example.motorcycleordermanagement.ui.edit.detailorder;

import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentEditDetailOrderBinding;
import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.ui.edit.EditViewModel;

import static com.example.motorcycleordermanagement.ui.edit.EditActivity.DATA;

public class EditDetailOrderFragment extends BaseFragment<FragmentEditDetailOrderBinding, EditDetailOrderViewModel> {
    public EditDetailOrderFragment() {
        super(R.layout.fragment_edit_detail_order);
    }

    public EditViewModel getActivityViewModel() {
        if(activityViewModel == null){
            activityViewModel = new ViewModelProvider(requireActivity()).get(EditViewModel.class);
        }
        return activityViewModel;
    }

    private EditViewModel activityViewModel;
    @Override
    public void createView() {
        viewModel.initDetailOrder((DetailOrder) getActivity().getIntent().getSerializableExtra(DATA));
        binding.success.setOnClickListener((v) -> {
            viewModel.setDetailOrder(binding.getDetailOrder());
            switch (getActivityViewModel().getTypeAction()){
                case ADD:
                    viewModel.addDetailOrder();
                    break;
                case EDIT:
                    viewModel.editDetailOrder();
                    break;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.setDetailOrder(viewModel.getDetailOrder());
    }

    @Override
    public EditDetailOrderViewModel getViewModel() {
        return new ViewModelProvider(this).get(EditDetailOrderViewModel.class);
    }
}
