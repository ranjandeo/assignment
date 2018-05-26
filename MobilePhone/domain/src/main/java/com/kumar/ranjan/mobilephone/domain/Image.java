package com.kumar.ranjan.mobilephone.domain;

public class Image {
    private static String EMPTY_STRING = "";

    private int id;
    private int mobileId;
    private String url;

    public Image() {
        url = EMPTY_STRING;
    }

    public Image(int id, int mobileId, String url) {
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
