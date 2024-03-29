package com.sosa.dummyapp.ui.settings;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    private static final String TAG = "SettingsViewModel";

    private final MutableLiveData<String> mText;

    public SettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is settings fragment");
        Log.i(TAG, "Updated settings textview");
    }

    public LiveData<String> getText() {
        return mText;
    }
}