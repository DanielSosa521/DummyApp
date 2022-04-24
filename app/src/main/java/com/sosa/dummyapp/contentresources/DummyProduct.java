package com.sosa.dummyapp.contentresources;

import android.graphics.drawable.Drawable;

/**
 * Class DummyProduct
 * This class was created for sandbox use with the Products found on dummyjson.com
 * It was used to practice using HTTPUrlConnection objects as a get request
 * and to re-learn how to use GSON/JSON to serialize/un-serialize JSON data
 * To create a class meant to interpret JSON data, add a member field that matches
 * the JSON dictionary of the data. Types must be compatible (Numbers to int, strings to String, string arrays to String[])
 * Example JSON of a dummyjson.com Product dictionary:
 * {"id":1,
 * "title":"iPhone 9",
 * "description":"An apple mobile which is nothing like apple",
 * "price":549,
 * "discountPercentage":12.96,
 * "rating":4.69,
 * "stock":94,
 * "brand":"Apple",
 * "category":"smartphones",
 * "thumbnail":"https://dummyjson.com/image/i/products/1/thumbnail.jpg",
 * "images":["https://dummyjson.com/image/i/products/1/1.jpg","https://dummyjson.com/image/i/products/1/2.jpg",
 * "https://dummyjson.com/image/i/products/1/3.jpg","https://dummyjson.com/image/i/products/1/4.jpg",
 * "https://dummyjson.com/image/i/products/1/thumbnail.jpg"]}
 */

public class DummyProduct {

    private final int id;
    private final String title;
    private final String description;
    private final int price;
    private final double discountPercentage;
    private final double rating;
    private final int stock;
    private final String brand;
    private final String category;
    private final String thumbnail;
    private final String[] images;
    private Drawable drawable;

    public DummyProduct(){
        id = -1;
        title = null;
        description = null;
        price = -1;
        discountPercentage = -1.0;
        rating = -1.0;
        stock = -1;
        brand = null;
        category = null;
        thumbnail = null;
        images = null;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public String getImageURL(){
        assert images != null;
        if (images.length != 0){
            return images[0];
        } else {
            return null;
        }
    }

    public String[] getImages() {
        return images;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }
}

