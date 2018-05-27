package com.kumar.ranjan.mobilephone.data.preference;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.data.entity.mapper.JsonMapper;
import com.kumar.ranjan.mobilephone.domain.Phone;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class FavoritesPreferenceStorageImpl implements FavoritesPrefereceStorage {
    private static final String FAVORITES_STORAGE_NAME = "FAV_MOBILE_PHONES";
    private static final String FIELD_KEY = "FAVORITES";

    private SharedPreferences favoritesPrefs;

    public FavoritesPreferenceStorageImpl(Context context) {
        favoritesPrefs = context.getSharedPreferences(FAVORITES_STORAGE_NAME, MODE_PRIVATE);
    }

    @Override
    public void storeFavorites(List<Phone> phoneList) {
        if (phoneList != null) {
            SharedPreferences.Editor editor = favoritesPrefs.edit();

            if (!phoneList.isEmpty()) {
                JsonMapper jsonMapper = new JsonMapper();
                editor.putString(FIELD_KEY, jsonMapper.transformPhoneListToJson(phoneList));
            } else {
                editor.clear();
            }
            editor.apply();
        }
    }

    @Override
    public List<Phone> retrieveFavoriteList() {
        List<Phone> phoneList = Lists.newArrayList();
        String favoritePhones = favoritesPrefs.getString(FIELD_KEY, "");
        if (!Strings.isNullOrEmpty(favoritePhones)) {
            JsonMapper mapper = new JsonMapper();
            phoneList.addAll(mapper.transformPhoneList(favoritePhones));
        }
        return phoneList;
    }

}
