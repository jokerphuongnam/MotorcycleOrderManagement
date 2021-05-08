package com.example.motorcycleordermanagement.ui.edit.detailorder;

import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentEditDetailOrderBinding;
import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.ui.edit.EditViewModel;
import com.example.schoolappliancesmanager.util.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.motorcycleordermanagement.ui.edit.EditActivity.DATA;

@AndroidEntryPoint
public class EditDetailOrderFragment extends BaseFragment<FragmentEditDetailOrderBinding, EditDetailOrderViewModel> {
    public EditDetailOrderFragment() {
        super(R.layout.fragment_edit_detail_order);
    }

    public EditViewModel getActivityViewModel() {
        if (activityViewModel == null) {
            activityViewModel = new ViewModelProvider(requireActivity()).get(EditViewModel.class);
        }
        return activityViewModel;
    }

    private EditViewModel activityViewModel;

    public ArrayAdapter<Motorcycle> getMotorcycleAdapter() {
        List<Motorcycle> motorcycles;
        if (viewModel.getMotorcycles().getValue() == null || viewModel.getMotorcycles().getValue().isEmpty()) {
            motorcycles = new ArrayList<>();
        } else {
            motorcycles = viewModel.getMotorcycles().getValue();
        }
        return new MotorcycleSpinnerAdapter(requireContext(), motorcycles);
    }

    public ArrayAdapter<String> getOrdersAdapter() {
        List<String> orders;
        if (viewModel.getOrders().getValue() == null || viewModel.getOrders().getValue().isEmpty()) {
            orders = new ArrayList<>();
        } else {
            orders = viewModel.getOrders().getValue().stream().map(order -> String.valueOf(order.getOrderId())).collect(Collectors.toList());
        }
        return new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, orders);
    }

    @Override
    public void createView() {
        viewModel.initDetailOrder((DetailOrder) getActivity().getIntent().getSerializableExtra(DATA));
        binding.success.setOnClickListener((v) -> {
            DetailOrder detailOrder = binding.getDetailOrder();
            binding.getDetailOrder().setCount(Integer.parseInt(binding.count.getText().toString()));
            if (detailOrder.getCount() == 0) {
                binding.detailOrderError.setVisibility(View.VISIBLE);
                binding.detailOrderError.setText(R.string.price_greater_than_zero);
                return;
            }
            detailOrder.setOrderId(viewModel.getOrders().getValue().get(binding.orderIdSpinner.getSelectedItemPosition()).getOrderId());
            detailOrder.setMotorcycleId(viewModel.getMotorcycles().getValue().get(binding.orderIdSpinner.getSelectedItemPosition()).getMotorcycleId());
            viewModel.setDetailOrder(detailOrder);
            switch (getActivityViewModel().getTypeAction()) {
                case ADD:
                    viewModel.addDetailOrder();
                    break;
                case EDIT:
                    viewModel.editDetailOrder();
                    break;
            }
        });
        viewModel.getOrders().observe(getViewLifecycleOwner(), (orders) -> {
            getOrdersAdapter().notifyDataSetChanged();
            binding.orderIdSpinner.setAdapter(getOrdersAdapter());
        });
        viewModel.getMotorcycles().observe(getViewLifecycleOwner(), (motorcycles) -> {
            getMotorcycleAdapter().notifyDataSetChanged();
            binding.motorcycleSpinner.setAdapter(getMotorcycleAdapter());
        });
        viewModel.getSuccess().observe(getViewLifecycleOwner(), (resource) -> {
            if (resource instanceof Resource.Loading) {
                binding.detailOrderError.setVisibility(View.GONE);
            } else if (resource instanceof Resource.Success) {
                getActivity().finish();
                showToast(getString(R.string.save_success));
            } else if (resource instanceof Resource.Error) {
                binding.detailOrderError.setText(R.string.save_fail);
                binding.detailOrderError.setVisibility(View.VISIBLE);
            }
        });
        binding.count.addTextChangedListener(new TextWatcher() {

            private String oldCount;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                oldCount = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().isEmpty()) {
                    int newCount = Integer.parseInt(s.toString());
                    int selectedItemPosition = binding.motorcycleSpinner.getSelectedItemPosition();
                    if (viewModel.getMotorcycles().getValue() != null && !viewModel.getMotorcycles().getValue().isEmpty()) {
                        Motorcycle motorcycle = viewModel.getMotorcycles().getValue().get(selectedItemPosition);
                        if (motorcycle.getCount() < newCount) {
                            new AlertDialog.Builder(requireContext()).setMessage(R.string.count_rather_than).show();
                            binding.count.setText(oldCount);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.setDetailOrder(viewModel.getDetailOrder());
        viewModel.initApplianceAndRoomName();
    }

    @Override
    public EditDetailOrderViewModel getViewModel() {
        return new ViewModelProvider(this).get(EditDetailOrderViewModel.class);
    }
}
