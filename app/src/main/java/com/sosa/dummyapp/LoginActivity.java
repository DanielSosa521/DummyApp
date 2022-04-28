package com.sosa.dummyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

/**
 * Video tutorial for login activity : https://www.youtube.com/watch?v=ayKMfVt2Sg4
 */

public class LoginActivity extends AppCompatActivity {

    ViewPager2 viewpager;
    TabLayout tabLayout;
    LoginFragmentAdapter adapter;
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //emulator host loopback url     //"http://127.0.0.1:5000";
        viewpager = findViewById(R.id.view_pager_login);
        tabLayout = findViewById(R.id.tab_layout_login);
        FragmentManager manager = getSupportFragmentManager();
        adapter = new LoginFragmentAdapter(manager, getLifecycle());
        Log.i(TAG, "Created adapter");
        viewpager.setAdapter(adapter);
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Register"));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //do tasks here

    }
}