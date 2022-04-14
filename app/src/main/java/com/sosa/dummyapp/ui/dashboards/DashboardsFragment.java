package com.sosa.dummyapp.ui.dashboards;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.sosa.dummyapp.R;

public class DashboardsFragment extends Fragment {

    ViewPager2 viewpager2;
    TabLayout tabLayout;
    FragmentAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardsViewModel dashboardsViewModel = new ViewModelProvider(this).get(DashboardsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboards, container, false);
        viewpager2 = root.findViewById(R.id.view_pager_frag);
//        viewpager2 = getView().findViewById(R.id.view_pager_frag);
//        tabLayout = getView().findViewById(R.id.tab_layout);
        tabLayout = root.findViewById(R.id.tab_layout_frag);
        FragmentManager manager = getParentFragmentManager();
        adapter = new FragmentAdapter(manager, getLifecycle());
        viewpager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.addTab(tabLayout.newTab().setText("Day"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        return root;

    }

}