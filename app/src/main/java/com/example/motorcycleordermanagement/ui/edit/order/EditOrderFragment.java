package com.example.motorcycleordermanagement.ui.edit.order;

import android.app.DatePickerDialog;

import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentEditOrderBinding;
import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.ui.edit.EditViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.motorcycleordermanagement.ui.edit.EditActivity.DATA;

@AndroidEntryPoint
public class EditOrderFragment extends BaseFragment<FragmentEditOrderBinding, EditOrderViewModel> {
    public EditOrderFragment() {
        super(R.layout.fragment_edit_order);
    }

    public EditViewModel getActivityViewModel() {
        if (activityViewModel == null) {
            activityViewModel = new ViewModelProvider(requireActivity()).get(EditViewModel.class);
        }
        return activityViewModel;
    }

    private EditViewModel activityViewModel;

    @Override
    public void createView() {
        viewModel.initOrder((Order) getActivity().getIntent().getSerializableExtra(DATA));
        binding.calendarChoose.setOnClickListener((v) -> getDatePicker().show());
        binding.success.setOnClickListener((v) -> {
            Order order = binding.getOrder();
            order.setPrice(Long.parseLong(binding.price.getText().toString()));
            viewModel.setOrder(order);
            switch (getActivityViewModel().getTypeAction()){
                case ADD:
                    viewModel.addOrder();
                    break;
                case EDIT:
                    viewModel.editOrder();
                    break;
            }
        });
        viewModel.getSuccess().observe(getViewLifecycleOwner(), (success) -> {
            getActivity().finish();
        });
    }

    private DatePickerDialog.OnDateSetListener datePickerCallBack;

    @Override
    protected DatePickerDialog.OnDateSetListener getDatePickerCallBack() {
        if (datePickerCallBack == null) {
            datePickerCallBack = (view, year, monthOfYear, dayOfMonth) -> {
                Calendar calendar = getCalendar();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                setDateText();
            };
        }
        return datePickerCallBack;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.setOrder(viewModel.getOrder());
        setDateText();
    }

    private Calendar calendar;

    @Override
    public Calendar getCalendar() {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        return calendar;
    }

    private void setDateText() {
        binding.dateOrder.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(getCalendar().getTime()));
        Order order = binding.getOrder();
        order.setDateOrder(getCalendar().getTimeInMillis());
        binding.setOrder(order);
    }

    @Override
    public EditOrderViewModel getViewModel() {
        return new ViewModelProvider(this).get(EditOrderViewModel.class);
    }
}
