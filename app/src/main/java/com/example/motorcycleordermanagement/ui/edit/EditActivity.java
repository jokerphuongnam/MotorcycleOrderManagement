package com.example.motorcycleordermanagement.ui.edit;

import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.ActivityEditBinding;
import com.example.motorcycleordermanagement.ui.base.BaseActivity;
import com.example.motorcycleordermanagement.ui.edit.detailorder.EditDetailOrderFragment;
import com.example.motorcycleordermanagement.ui.edit.motorcycle.EditMotorcycleFragment;
import com.example.motorcycleordermanagement.ui.edit.order.EditOrderFragment;

import static com.example.motorcycleordermanagement.ui.edit.EditActivity.TypeAction.ADD;
import static com.example.motorcycleordermanagement.ui.edit.EditActivity.TypeAction.EDIT;

public class EditActivity extends BaseActivity<ActivityEditBinding, EditViewModel> {
    public EditActivity() {
        super(R.layout.activity_edit);
    }

    private ActionBar actionBar;

    private void setUpActionBar() {
        setSupportActionBar(binding.toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setUpFragment() {
        switch (viewModel.getTypeEdit()) {
            case MOTORCYCLE:
                openFragment(new EditMotorcycleFragment(), getString(R.string.motorcycle));
                break;
            case DETAIL_ORDER:
                openFragment(new EditDetailOrderFragment(), getString(R.string.detail_order));
                break;
            case ORDER:
                openFragment(new EditOrderFragment(), getString(R.string.order));
                break;
        }
    }

    private <F extends Fragment> void openFragment(F fragment, String tag) {
        openFragment(R.id.add, fragment, tag);
    }

    private void initData() {
        viewModel.setTypeEdit(TypeEdit.valueOf(getIntent().getSerializableExtra(TYPE_EDIT).toString()));
        viewModel.setTypeAction(getIntent().hasExtra(DATA) ? EDIT : ADD);
    }

    @Override
    public void createView() {
        initData();
        setUpActionBar();
        setUpFragment();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public EditViewModel getViewModel() {
        return new ViewModelProvider(this).get(EditViewModel.class);
    }

    public static String TYPE_ACTION = "TypeAction";
    public static String TYPE_EDIT = "TypeEdit";
    public static String DATA = "Data";

    public enum TypeAction {
        ADD, EDIT;
    }

    public enum TypeEdit {
        MOTORCYCLE, DETAIL_ORDER, ORDER;
    }
}
