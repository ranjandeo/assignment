package com.kumar.ranjan.mobilephone.data.entity;

import com.google.gson.annotations.SerializedName;

public class PhoneEntity {
    private static String EMPTY_STRING = "";

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("brand")
    private String brand;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbImageURL")
    private String thumbImageURL;

    @SerializedName("price")
    private double price;

    @SerializedName("rating")
    private double rating;

    public PhoneEntity() {
        name = EMPTY_STRING;
        brand = EMPTY_STRING;
        description = EMPTY_STRING;
        thumbImageURL = EMPTY_STRING;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbImageURL() {
        return thumbImageURL;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }
}
