package com.sosa.dummyapp.ui.controller;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sosa.dummyapp.R;
import com.sosa.dummyapp.ui.settings.SettingsViewModel;

public class ControllerFragment extends Fragment {

    private static final String TAG = "ControllerFragment";

    private ControllerViewModel mViewModel;

    public static ControllerFragment newInstance() {
        return new ControllerFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "Created new instance");
        ControllerViewModel controllerViewModel = new ViewModelProvider(this).get(ControllerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_controller, container, false);
        final TextView textView = root.findViewById(R.id.text_controller);
        controllerViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


}