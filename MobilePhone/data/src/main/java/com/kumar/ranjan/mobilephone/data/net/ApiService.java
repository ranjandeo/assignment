package com.kumar.ranjan.mobilephone.data.net;

import com.kumar.ranjan.mobilephone.data.entity.ImageEntity;
import com.kumar.ranjan.mobilephone.data.entity.PhoneEntity;

import java.util.List;

import io.reactivex.Observable;

public interface ApiService {

    String PHONE_LIST_BASE_URL = "https://scb-test-mobile.herokuapp.com/api/mobiles/";
    String IMAGE_LIST_BASE_URL = "https://scb-test-mobile.herokuapp.com/api/mobiles/{mobile_id}/images/";

    Observable<List<PhoneEntity>> phoneEntityList();

    Observable<List<ImageEntity>> imageEntityList(int mobileId);
}
