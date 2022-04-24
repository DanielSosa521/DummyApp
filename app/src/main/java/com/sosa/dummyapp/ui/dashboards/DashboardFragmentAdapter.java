package com.sosa.dummyapp.ui.dashboards;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sosa.dummyapp.contentresources.DashboardResource;
import com.sosa.dummyapp.ui.dashboards.fragments.MonthFragment;
import com.sosa.dummyapp.ui.dashboards.fragments.DayFragment;
import com.sosa.dummyapp.ui.dashboards.fragments.PlugsFragment;

public class DashboardFragmentAdapter extends FragmentStateAdapter {


    public DashboardFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    Fragment monthFrag = new MonthFragment();
    Fragment dayFrag = new DayFragment();
    Fragment plugFrag = new PlugsFragment();

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            default: return monthFrag;
            case 1 : return dayFrag;
            case 2 : return plugFrag;
        }
    }

    public void postMonthResource(DashboardResource res){
        ((MonthFragment)monthFrag).updateGraphWithResource(res);
    }

    public void clearMonth(){
        ((MonthFragment)monthFrag).clearGraph();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
