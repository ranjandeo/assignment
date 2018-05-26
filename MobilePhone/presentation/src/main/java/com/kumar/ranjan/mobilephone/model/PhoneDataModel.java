package com.kumar.ranjan.mobilephone.model;

import org.parceler.Parcel;

@Parcel
public class PhoneDataModel {
    private static String EMPTY_STRING = "";

    int id;
    String name;
    String brand;
    String description;
    String thumbImageURL;
    double price;
    double rating;

    public PhoneDataModel() {
        name = EMPTY_STRING;
        brand = EMPTY_STRING;
        description = EMPTY_STRING;
        thumbImageURL = EMPTY_STRING;
    }

    public PhoneDataModel(int id, String name, String brand, String description, String thumbImageURL, double price, double rating) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.description = description;
        this.thumbImageURL = thumbImageURL;
        this.price = price;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbImageURL() {
        return thumbImageURL;
    }

    public void setThumbImageURL(String thumbImageURL) {
        this.thumbImageURL = thumbImageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
