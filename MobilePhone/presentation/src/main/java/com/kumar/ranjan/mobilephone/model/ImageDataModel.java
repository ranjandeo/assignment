package com.kumar.ranjan.mobilephone.model;

public class ImageDataModel {
    private static String EMPTY_STRING = "";

    private int id;
    private int mobileId;
    private String url;

    public ImageDataModel() {
        url = EMPTY_STRING;
    }

    public ImageDataModel(int id, int mobileId, String url) {
        this.id = id;
        this.mobileId = mobileId;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMobileId() {
        return mobileId;
    }

    public void setMobileId(int mobileId) {
        this.mobileId = mobileId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
