package com.kumar.ranjan.mobilephone.data.repository;

import com.kumar.ranjan.mobilephone.data.entity.mapper.ImageEntityMapper;
import com.kumar.ranjan.mobilephone.data.entity.mapper.PhoneEntityMapper;
import com.kumar.ranjan.mobilephone.data.repository.datasource.NetworkDataStore;
import com.kumar.ranjan.mobilephone.data.repository.datasource.PhoneListDataStoreFactory;
import com.kumar.ranjan.mobilephone.domain.Image;
import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.repository.NetworkRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class NetworkRepositoryImpl implements NetworkRepository {

    private final PhoneListDataStoreFactory phoneListDataStoreFactory;
    private final PhoneEntityMapper phoneEntityMapper;
    private final ImageEntityMapper imageEntityMapper;

    @Inject
    public NetworkRepositoryImpl(PhoneListDataStoreFactory phoneListDataStoreFactory,
                                 PhoneEntityMapper phoneEntityMapper,
                                 ImageEntityMapper imageEntityMapper) {
        this.phoneListDataStoreFactory = phoneListDataStoreFactory;
        this.phoneEntityMapper = phoneEntityMapper;
        this.imageEntityMapper = imageEntityMapper;
    }

    @Override
    public Observable<List<Phone>> phoneList() {
        NetworkDataStore networkDataStore = phoneListDataStoreFactory.createCloudDataStore();
        return networkDataStore.phoneEntityList().map(phoneEntityMapper::transform);
    }

    @Override
    public Observable<List<Image>> imageList(int mobileId) {
        NetworkDataStore networkDataStore = phoneListDataStoreFactory.createCloudDataStore();
        return networkDataStore.imageEntityList(mobileId).map(imageEntityMapper::transform);
    }
}
