package com.sosa.dummyapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sosa.dummyapp.tasks.LoginTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private EditText username;
    private EditText password;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //work here
        username = requireView().findViewById(R.id.login_username_edittext);
        password = requireView().findViewById(R.id.login_password_edittext);
        Button loginButton = requireView().findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view12 -> {
            Log.i(TAG, "Login button pressed!");
            doLogin();
        });
        FloatingActionButton navFloatingActionBtn = requireView().findViewById(R.id.navFloatingActionBtn);
        navFloatingActionBtn.setOnClickListener(view1 -> {
            Log.i(TAG, "FAB pressed!");
            openActivity();
        });
    }

    public void openActivity(){
        Log.i(TAG, "Going to nav activity!");
        Intent intent = new Intent(this.getContext(), NavigationMenuActivity.class);
        startActivity(intent);
    }

    private void doLogin(){
        String user = username.getText().toString();
        String pass = password.getText().toString();
        if (user.isEmpty() ||
                pass.isEmpty()){
            Toast.makeText(getContext(), "Please enter both values",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i(TAG, "Logging in with " + user + " : " + pass);
        new LoginTask(this).execute(user, pass);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}