package com.sosa.dummyapp.ui.dashboards;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sosa.dummyapp.R;

public class DashboardsFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardsViewModel dashboardsViewModel = new ViewModelProvider(this).get(DashboardsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboards, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboards);
        dashboardsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

}