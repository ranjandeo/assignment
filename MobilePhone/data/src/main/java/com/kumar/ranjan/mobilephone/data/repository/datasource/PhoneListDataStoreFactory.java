package com.kumar.ranjan.mobilephone.data.repository.datasource;

import com.kumar.ranjan.mobilephone.data.entity.mapper.JsonMapper;
import com.kumar.ranjan.mobilephone.data.net.ApiService;
import com.kumar.ranjan.mobilephone.data.net.ApiServiceImpl;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PhoneListDataStoreFactory {

    private final Context context;

    @Inject
    public PhoneListDataStoreFactory(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    public NetworkDataStore createCloudDataStore() {
        JsonMapper jsonMapper = new JsonMapper();

        ApiService apiService = new ApiServiceImpl(context, jsonMapper);

        return new NetworkDataStoreImpl(apiService);
    }
}
