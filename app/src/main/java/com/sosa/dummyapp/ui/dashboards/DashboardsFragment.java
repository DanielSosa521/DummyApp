package com.sosa.dummyapp.ui.dashboards;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.sosa.dummyapp.contentresources.DashboardResource;
import com.sosa.dummyapp.tasks.GetDashboardContentTask;
import com.sosa.dummyapp.R;
import com.sosa.dummyapp.databinding.FragmentDashboardsBinding;

public class DashboardsFragment extends Fragment {

    ViewPager2 viewpager2;
    TabLayout tabLayout;
    DashboardFragmentAdapter adapter;
    private FragmentDashboardsBinding binding;

    private static final String TAG = "DashboardFragment";
    private static String localhost; //emulator host loopback url     //"http://127.0.0.1:5000";
    private static String webhost = "https://smartplugapi-dummy.herokuapp.com/";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardsViewModel dashboardsViewModel = new ViewModelProvider(this).get(DashboardsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboards, container, false);
        localhost = getResources().getString(R.string.emulator_local_host);
        binding = FragmentDashboardsBinding.inflate(inflater, container, false);
        viewpager2 = root.findViewById(R.id.view_pager_frag);
//        viewpager2 = getView().findViewById(R.id.view_pager_frag);
//        tabLayout = getView().findViewById(R.id.tab_layout);
        tabLayout = root.findViewById(R.id.tab_layout_frag);
        FragmentManager manager = getParentFragmentManager();
        adapter = new DashboardFragmentAdapter(manager, getLifecycle());
        viewpager2.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Month"));
        tabLayout.addTab(tabLayout.newTab().setText("Day"));
        tabLayout.addTab(tabLayout.newTab().setText("Plugs"));
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

        GetDashboardContentTask task = new GetDashboardContentTask(this);
        task.execute(webhost + "/dashboard/month");

        return root;

    }

    public void updateMonthGraph(DashboardResource res){
        Log.i(TAG, "DashboardFragment got Resource from AsyncTask");
        View root = binding.getRoot();
        adapter.clearMonth();
        adapter.postMonthResource(res);
    }

}