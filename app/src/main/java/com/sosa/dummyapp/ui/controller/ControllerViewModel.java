package com.sosa.dummyapp.ui.controller;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ControllerViewModel extends ViewModel {

    private static final String TAG = "ControllerViewModel";

    private final MutableLiveData<String> mText;


    public ControllerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is controller fragment");
        Log.i(TAG, "Updated controller textview");
    }

    public String receivedData;

    public void openDialog(){

    }



    public LiveData<String> getText() {
        return mText;
    }


}