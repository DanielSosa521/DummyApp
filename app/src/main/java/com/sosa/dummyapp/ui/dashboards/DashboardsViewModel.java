package com.sosa.dummyapp.ui.dashboards;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public DashboardsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboards fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}