package com.example.motorcycleordermanagement.ui.main;

import android.content.Intent;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.ActivityMainBinding;
import com.example.motorcycleordermanagement.ui.base.BaseActivity;
import com.example.motorcycleordermanagement.ui.edit.EditActivity;
import com.example.motorcycleordermanagement.ui.main.detailorder.DetailOrderFragment;
import com.example.motorcycleordermanagement.ui.main.motorcycle.MotorcycleFragment;
import com.example.motorcycleordermanagement.ui.main.order.OrderFragment;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.motorcycleordermanagement.ui.edit.EditActivity.TYPE_EDIT;

@AndroidEntryPoint
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {

    private MainAdapter mainAdapter;

    @NotNull
    public MainAdapter getMainAdapter() {
        if (mainAdapter == null) {
            mainAdapter = new MainAdapter(this);
            mainAdapter.addFragment(new DetailOrderFragment());
            mainAdapter.addFragment(new MotorcycleFragment());
            mainAdapter.addFragment(new OrderFragment());
        }
        return mainAdapter;
    }

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    public void createView() {
        binding.mainContainer.setAdapter(getMainAdapter());
        binding.mainTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.mainContainer.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.mainContainer.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.mainTab.selectTab(binding.mainTab.getTabAt(position));
            }
        });
        binding.addBtn.setOnClickListener((v) -> {
            Intent intent = new Intent(this, EditActivity.class);
            intent.putExtra(TYPE_EDIT, EditActivity.TypeEdit.values()[binding.mainContainer.getCurrentItem()]);
            startActivity(intent);
        });
    }

    @Override
    public MainViewModel getViewModel() {
        return new ViewModelProvider(this).get(MainViewModel.class);
    }
}