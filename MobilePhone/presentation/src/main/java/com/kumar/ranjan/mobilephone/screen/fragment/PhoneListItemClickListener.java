package com.kumar.ranjan.mobilephone.screen.fragment;

import com.kumar.ranjan.mobilephone.model.PhoneDataModel;

public interface PhoneListItemClickListener {
    void onPhoneItemClicked(PhoneDataModel phoneDataModel);
    void onMarkedAsFavorite(PhoneDataModel phoneDataModel);
    void onRemovedFromFavorite(PhoneDataModel phoneDataModel);
}
