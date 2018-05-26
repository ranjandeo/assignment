package com.kumar.ranjan.mobilephone.data.repository.datasource;

import com.kumar.ranjan.mobilephone.data.entity.ImageEntity;
import com.kumar.ranjan.mobilephone.data.entity.PhoneEntity;

import java.util.List;

import io.reactivex.Observable;

public interface NetworkDataStore {

    Observable<List<PhoneEntity>> phoneEntityList();

    Observable<List<ImageEntity>> imageEntityList(int mobileId);
}
