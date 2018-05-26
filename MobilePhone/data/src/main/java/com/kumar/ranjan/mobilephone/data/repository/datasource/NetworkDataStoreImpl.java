package com.kumar.ranjan.mobilephone.data.repository.datasource;

import com.kumar.ranjan.mobilephone.data.entity.ImageEntity;
import com.kumar.ranjan.mobilephone.data.entity.PhoneEntity;
import com.kumar.ranjan.mobilephone.data.net.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class NetworkDataStoreImpl implements NetworkDataStore {
    private final ApiService apiService;

    @Inject
    public NetworkDataStoreImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<List<PhoneEntity>> phoneEntityList() {
        return apiService.phoneEntityList();
    }

    @Override
    public Observable<List<ImageEntity>> imageEntityList(int mobileId) {
        return apiService.imageEntityList(mobileId);
    }
}
