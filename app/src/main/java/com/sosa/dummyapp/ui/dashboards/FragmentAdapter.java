package com.sosa.dummyapp.ui.dashboards;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sosa.dummyapp.ui.dashboards.fragments.FirstFragment;
import com.sosa.dummyapp.ui.dashboards.fragments.SecondFragment;

public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            default: return new FirstFragment();
            case 1 : return new SecondFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
