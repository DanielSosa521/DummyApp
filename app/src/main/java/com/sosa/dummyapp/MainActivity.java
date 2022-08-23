package com.sosa.dummyapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * NOTE : This class is deprecated and no longer in use as of April 21 2022
 *  This class is only kept if for some reason it is necessary, though it shouldn't be
 *  Class should eventually be deleted
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {                    //onCreate method "initializes" the activity. Happens on app startup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testButton = findViewById(R.id.testButton);                  //For example here, we define that the button will do *this*
        testButton.setOnClickListener(view -> {
            Log.i(TAG, "BUTTON PRESSED");
            TextView titleTextView = findViewById(R.id.titleTextView);
            titleTextView.setText(R.string.helloworld);                     //Pressing button will get a product data and display it
//            getAndShowProduct(index);
            index++;
        });


    }


}