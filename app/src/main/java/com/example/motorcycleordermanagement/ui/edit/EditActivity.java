package com.example.motorcycleordermanagement.ui.edit;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.ActivityEditBinding;
import com.example.motorcycleordermanagement.ui.base.BaseActivity;

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

    @Override
    public void createView() {
        setUpActionBar();
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
