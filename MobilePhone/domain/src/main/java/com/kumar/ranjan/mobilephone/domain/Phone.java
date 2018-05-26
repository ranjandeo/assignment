package com.kumar.ranjan.mobilephone.domain;

public class Phone {
    private static String EMPTY_STRING = "";

    private int id;
    private String name;
    private String brand;
    private String description;
    private String thumbImageURL;
    private double price;
    private double rating;

    public Phone() {
        name = EMPTY_STRING;
        brand = EMPTY_STRING;
        description = EMPTY_STRING;
        thumbImageURL = EMPTY_STRING;
    }

    public Phone(int id, String name, String brand, String description, String thumbImageURL, double price, double rating) {
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
