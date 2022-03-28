package com.sosa.dummyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int index = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(view -> {
            Log.i(TAG, "BUTTON PRESSED");
            TextView titleTextView = findViewById(R.id.titleTextView);
            titleTextView.setText(R.string.helloworld);
            getAndShowProduct(index);
            index++;
        });
    }

    @SuppressLint("SetTextI18n")
    public void displayProduct(DummyProduct prod){
        TextView titleTextView = findViewById(R.id.titleTextView);
        TextView descTextView = findViewById(R.id.descTextView);
        TextView priceTextView = findViewById(R.id.priceTextView);
        TextView ratingTextView = findViewById(R.id.ratingTextView);
        TextView brandTextView = findViewById(R.id.brandTextView);
        ImageView productImageView = findViewById(R.id.productImageView);
        titleTextView.setText(prod.getTitle());
        descTextView.setText("Description: " + prod.getDescription());
        priceTextView.setText(String.format("Price: %s", prod.getPrice()));
        ratingTextView.setText(String.format("Rating:%s", prod.getRating()));
        brandTextView.setText("Brand: " + prod.getBrand());
        productImageView.setImageDrawable(prod.getDrawable());
        //TODO : format product image before setting productImageView
    }

    private void getAndShowProduct(int index){
        Log.i(TAG, "getAndShowProduct(" + index + ")");
        GetUrlContentTask urlContentTask = new GetUrlContentTask(this);
        String myURL = "https://dummyjson.com/products/" + index;
        Log.i(TAG, "Target URL : " + myURL);
        urlContentTask.execute(myURL);
    }

}