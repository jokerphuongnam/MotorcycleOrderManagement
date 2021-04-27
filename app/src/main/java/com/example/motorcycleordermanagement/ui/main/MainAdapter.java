package com.example.motorcycleordermanagement.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends FragmentStateAdapter {

    private List<Fragment> fragments;

    public List<Fragment> getFragments() {
        if(fragments == null){
            fragments = new ArrayList<>();
        }
        return fragments;
    }

    public MainAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public interface FragmentAction {
        void action(Fragment fragment);
    }

    public <Frag extends Fragment> void addFragment(@NotNull Frag fragment, @Nullable FragmentAction action) {
        getFragments().add(fragment);
        if(action != null){
            action.action(fragment);
        }
    }

    public <Frag extends Fragment> void addFragment(@NotNull Frag fragment) {
        addFragment(fragment, null);
    }

    public Fragment getFragment(int position){
        return fragments.get(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
