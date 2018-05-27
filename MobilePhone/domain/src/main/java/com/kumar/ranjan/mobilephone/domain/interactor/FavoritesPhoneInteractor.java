package com.kumar.ranjan.mobilephone.domain.interactor;

import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.repository.FavoritesRepository;

import java.util.List;

import javax.inject.Inject;

public class FavoritesPhoneInteractor {

    private final FavoritesRepository favoritesRepository;

    @Inject
    public FavoritesPhoneInteractor(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    public List<Phone> getFavoritesPhonesList() {
        return favoritesRepository.favoritePhonesList();
    }

    public void storeFavoritesPhonesList(List<Phone> phoneList) {
        favoritesRepository.storeFavoritePhonesList(phoneList);
    }
}
