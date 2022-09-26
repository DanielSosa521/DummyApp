package com.sosa.dummyapp;

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

import com.sosa.dummyapp.tasks.RegisterTask;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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

    EditText email;
    EditText username;
    EditText password;
    EditText confirmedPassword;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //work here
        email = requireView().findViewById(R.id.register_email_edittext);
        username = requireView().findViewById(R.id.register_username_edittext);
        password = requireView().findViewById(R.id.register_password_edittext);
        confirmedPassword = requireView().findViewById(R.id.register_confirmpassword_edittext);
        Button registerButton = requireView().findViewById(R.id.registerButton);
        registerButton.setOnClickListener(view1 -> {
            Log.i(TAG, "Register button pressed!");
            doRegister();
        });

    }


    private void doRegister(){
        String em = email.getText().toString();
        String uname = username.getText().toString();                       //Grab strings from inputs
        String pass = password.getText().toString();
        String confPword = confirmedPassword.getText().toString();

        if (em.isEmpty() || uname.isEmpty() || pass.isEmpty() || confPword.isEmpty()){      //Make sure values are not empty
            Log.i(TAG, "Empty field in register fields");
            Toast.makeText(getContext(), "Please enter all values",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (!pass.equals(confPword)){               //Check password == confirmed password
            Log.i(TAG, "Passwords to not match");
            Toast.makeText(getContext(), "Confirmed password does not match password. Please fix", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i(TAG, "Registering : " + em + ", " + uname +", " + pass
         + ", " + confPword);
        //TODO : Execute POST request to API with register info payload
        JSONObject postData = new JSONObject();
        try {
            postData.put("email", em);
            postData.put("username", uname);
            postData.put("password", pass);
            Log.i(TAG, "Launched RegisterTask with postData=" + postData.toString());
            RegisterTask registerTask = new RegisterTask(this);
            registerTask.execute(postData.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}