package com.kumar.ranjan.mobilephone.data.repository;

import com.kumar.ranjan.mobilephone.data.preference.FavoritesPrefereceStorage;
import com.kumar.ranjan.mobilephone.data.repository.datasource.PhoneListDataStoreFactory;
import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.repository.FavoritesRepository;

import java.util.List;

import javax.inject.Inject;

public class FavoritesRepostoryImpl implements FavoritesRepository {

    private final PhoneListDataStoreFactory phoneListDataStoreFactory;

    @Inject
    public FavoritesRepostoryImpl(PhoneListDataStoreFactory phoneListDataStoreFactory) {
        this.phoneListDataStoreFactory = phoneListDataStoreFactory;
    }

    @Override
    public List<Phone> favoritePhonesList() {
        FavoritesPrefereceStorage favoritesPrefereceStorage = phoneListDataStoreFactory.createFavoritesPhoneStorage();
        return favoritesPrefereceStorage.retrieveFavoriteList();
    }

    @Override
    public void storeFavoritePhonesList(List<Phone> phoneList) {
        FavoritesPrefereceStorage favoritesPrefereceStorage = phoneListDataStoreFactory.createFavoritesPhoneStorage();
        favoritesPrefereceStorage.storeFavorites(phoneList);
    }
}
