package com.kumar.ranjan.mobilephone.data.entity;

import com.google.gson.annotations.SerializedName;

public class ImageEntity {
    private static String EMPTY_STRING = "";

    @SerializedName("id")
    private int id;

    @SerializedName("mobile_id")
    private int mobileId;

    @SerializedName("url")
    private String url;

    public ImageEntity() {
        url = EMPTY_STRING;
    }

    public int getId() {
        return id;
    }

    public int getMobileId() {
        return mobileId;
    }

    public String getUrl() {
        return url;
    }
}
