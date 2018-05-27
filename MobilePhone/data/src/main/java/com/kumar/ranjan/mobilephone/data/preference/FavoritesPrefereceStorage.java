package com.kumar.ranjan.mobilephone.data.preference;

import com.kumar.ranjan.mobilephone.domain.Phone;

import java.util.List;

public interface FavoritesPrefereceStorage {
    void storeFavorites(List<Phone> phoneList);

    List<Phone> retrieveFavoriteList();
}
