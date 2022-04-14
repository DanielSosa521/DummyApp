package com.sosa.dummyapp.ui.dashboards;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sosa.dummyapp.ui.dashboards.fragments.MonthFragment;
import com.sosa.dummyapp.ui.dashboards.fragments.DayFragment;
import com.sosa.dummyapp.ui.dashboards.fragments.PlugsFragment;

public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            default: return new MonthFragment();
            case 1 : return new DayFragment();
            case 2 : return new PlugsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
