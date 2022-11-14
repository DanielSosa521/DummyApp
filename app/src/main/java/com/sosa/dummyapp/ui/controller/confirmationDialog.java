package com.sosa.dummyapp.ui.controller;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.sosa.dummyapp.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class confirmationDialog extends AppCompatDialogFragment {

    private static final String TAG = "ConfirmationDialog";

    public Button buttonReference;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "Creating confirmation dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Energy Saving Notice")
                .setMessage("Power is expensive at the moment. " +
                        "Would you like to power on anyway?")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG, "Sending confirm signal");
                        OkHttpClient client = new OkHttpClient();
                        String url = "https://smartplugapiv2.onrender.com/mqtt/confirm";
                        Request request = new Request.Builder()
                                .url(url)
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                if(response.isSuccessful()) {
                                    String myResponse = response.body().string();
                                    System.out.println(myResponse);
                                }
                            }
                        });

                        buttonReference.setBackgroundColor(Color.BLUE);      //if confirm after all update button
                        buttonReference.setText(R.string.turnplug1off);

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i(TAG, "Power on aborted");
                    }
                });
        return builder.create();
    }

}
