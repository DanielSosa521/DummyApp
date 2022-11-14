package com.sosa.dummyapp.ui.controller;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sosa.dummyapp.MainActivity;
import com.sosa.dummyapp.NavigationMenuActivity;
import com.sosa.dummyapp.R;

import com.sosa.dummyapp.ui.settings.SettingsViewModel;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ControllerFragment extends Fragment {
    // Status of the buttons
    // This should get info from the server eventually
    boolean Button1 = false;
    boolean Button2 = false;
    boolean Button3 = false;
    private static final String TAG = "ControllerFragment";
    ViewModel viewModel;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "Created new instance");
        OkHttpClient client = new OkHttpClient();
        View view = inflater.inflate(R.layout.fragment_controller, container, false);
        viewModel = new ViewModelProvider(this).get(ControllerViewModel.class);

        Button button1 = (Button) view.findViewById(R.id.SendPowerSignal1);
        button1.setBackgroundColor(Color.GREEN);
        button1.setText(R.string.turnplug1on);
        button1.setOnClickListener(v -> {
            // Do Something
            Toast.makeText(super.getContext(), "Button 1 Pressed", Toast.LENGTH_SHORT).show();
            if(!Button1) {
//                try {
//                    httpRequest.run("https://smartplugapiv2.onrender.com/mqtt/on", viewModel);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                String url = "https://smartplugapiv2.onrender.com/mqtt/on";
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
                        if(response.isSuccessful()){
                            String myResponse = response.body().string();
                            System.out.println(myResponse);
                            if(myResponse.contains("confirm?")){
                                openDialog();
                            }
                        }
                    }
                });

            } else{
//                try {
//                    httpRequest.run("https://smartplugapiv2.onrender.com/mqtt/off", viewModel);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
                String url = "https://smartplugapiv2.onrender.com/mqtt/off";
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
                                                        if(response.isSuccessful()){
                                                            String myResponse = response.body().string();
                                                            }
                                                        }
                                                    });

            }
            Button1 = !Button1;
        });

        Button button2 = (Button) view.findViewById(R.id.SendPowerSignal2);
        button2.setBackgroundColor(Color.GREEN);
        button2.setText(R.string.turnplug2on);
        button2.setOnClickListener(v -> {
            // Do Something
            Toast.makeText(super.getContext(), "Button 2 Pressed", Toast.LENGTH_SHORT).show();
            if(!Button2) {
                try {
                    httpRequest.run("https://smartplugapiv2.onrender.com/mqtt/on");
                    button2.setBackgroundColor(Color.BLUE);
                    button2.setText(R.string.turnplug2off);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                try {
                    httpRequest.run("https://smartplugapiv2.onrender.com/mqtt/off");
                    button2.setBackgroundColor(Color.GREEN);
                    button2.setText(R.string.turnplug2on);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Button2 = !Button2;
        });

        Button button3 = (Button) view.findViewById(R.id.SendPowerSignal3);
        button3.setText(R.string.turnplug3on);
        button3.setBackgroundColor(Color.GREEN);
        button3.setOnClickListener(v -> {
            // Do Something
            Toast.makeText(super.getContext(), "Button 3 Pressed", Toast.LENGTH_SHORT).show();
            if(!Button3) {
                try {
                    httpRequest.run("https://smartplugapiv2.onrender.com/mqtt/on");
                    button3.setBackgroundColor(Color.BLUE);
                    button3.setText(R.string.turnplug3off);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else{
                try {
                    httpRequest.run("https://smartplugapiv2.onrender.com/mqtt/off");
                    button3.setBackgroundColor(Color.GREEN);
                    button3.setText(R.string.turnplug3on);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Button3 = !Button3;
        });
        return view;

    }

    public void openDialog(){

        confirmationDialog ConfirmationDialog = new confirmationDialog();
        ConfirmationDialog.show(getActivity().getSupportFragmentManager(), "Dialog box");
    }

}