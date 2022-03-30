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
    protected void onCreate(Bundle savedInstanceState) {                    //onCreate method "initializes" the activity. Happens on app startup
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testButton = findViewById(R.id.testButton);                  //For example here, we define that the button will do *this*
        testButton.setOnClickListener(view -> {
            Log.i(TAG, "BUTTON PRESSED");
            TextView titleTextView = findViewById(R.id.titleTextView);
            titleTextView.setText(R.string.helloworld);                     //Pressing button will get a product data and display it
            getAndShowProduct(index);
            index++;
        });
    }

    @SuppressLint("SetTextI18n")
    public void displayProduct(DummyProduct prod){
        TextView titleTextView = findViewById(R.id.titleTextView);          //Every "thing" on screen is called a "view"
        TextView descTextView = findViewById(R.id.descTextView);            //There are different views : textView, imageView, Button,
        TextView priceTextView = findViewById(R.id.priceTextView);          //To refer to a certain view, we use findViewById(<viewID>)
        TextView ratingTextView = findViewById(R.id.ratingTextView);
        TextView brandTextView = findViewById(R.id.brandTextView);
        ImageView productImageView = findViewById(R.id.productImageView);

        titleTextView.setText(prod.getTitle());                             //Then we can call methods on that view, such as textView.setText("stuff")
        descTextView.setText("Description: " + prod.getDescription());
        priceTextView.setText(String.format("Price: %s", prod.getPrice()));
        ratingTextView.setText(String.format("Rating:%s", prod.getRating()));
        brandTextView.setText("Brand: " + prod.getBrand());
        productImageView.setImageDrawable(prod.getDrawable());
        //TODO : format product image before setting productImageView
    }

    private void getAndShowProduct(int index){                              //This is my method to get product data from dummyjson.com
        Log.i(TAG, "getAndShowProduct(" + index + ")");
        GetUrlContentTask urlContentTask = new GetUrlContentTask(this);     //the task then calls displayProduct() to update all the views
        String myURL = "https://dummyjson.com/products/" + index;
        Log.i(TAG, "Target URL : " + myURL);
        urlContentTask.execute(myURL);
    }

}