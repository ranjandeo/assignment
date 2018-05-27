package com.kumar.ranjan.mobilephone.domain.repository;

import com.kumar.ranjan.mobilephone.domain.Phone;

import java.util.List;

public interface FavoritesRepository {
    List<Phone> favoritePhonesList();

    void storeFavoritePhonesList(List<Phone> phoneLsit);
}
