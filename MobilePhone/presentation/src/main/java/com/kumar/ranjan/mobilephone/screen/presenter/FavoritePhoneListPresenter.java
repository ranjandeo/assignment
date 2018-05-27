package com.kumar.ranjan.mobilephone.screen.presenter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.kumar.ranjan.mobilephone.di.PerActivity;
import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.interactor.FavoritesPhoneInteractor;
import com.kumar.ranjan.mobilephone.helper.PhoneListSorter;
import com.kumar.ranjan.mobilephone.mapper.PhoneDataModelMapper;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;
import com.kumar.ranjan.mobilephone.screen.fragment.FavoriteListScreen;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@PerActivity
public class FavoritePhoneListPresenter {
    private FavoriteListScreen favoriteListScreen;

    private final FavoritesPhoneInteractor favoritesPhoneInteractor;
    private final PhoneDataModelMapper phoneDataModelMapper;
    private final Map<Integer, PhoneDataModel> favPhoneMap;

    @Inject
    public FavoritePhoneListPresenter(FavoritesPhoneInteractor favoritesPhoneInteractor,
                              PhoneDataModelMapper phoneDataModelMapper) {
        this.favoritesPhoneInteractor = favoritesPhoneInteractor;
        this.phoneDataModelMapper = phoneDataModelMapper;
        favPhoneMap = Maps.newHashMap();
    }

    public void setView(@NonNull FavoriteListScreen view) {
        favoriteListScreen = view;
    }

    public void destroy() {
        favoriteListScreen = null;
    }

    public void initialize() {
        getFavoritesPhoneList();
    }

    public void onPhoneListItemClicked(PhoneDataModel phoneDataModel) {
        favoriteListScreen.showPhoneDetails(phoneDataModel);
    }

    private void showPhoneListInView() {
        favoriteListScreen.displayFavoriteList(Lists.newArrayList(favPhoneMap.values()));

        if (favPhoneMap.isEmpty()) {
            favoriteListScreen.showNoFavoritesMessage();
        } else {
            favoriteListScreen.hideErrorMessage();
        }
    }

    public void applySorting(SortOptionType sortOptionType) {
        PhoneListSorter sorter = new PhoneListSorter();
        favoriteListScreen.displayFavoriteList(sorter.sort(Lists.newArrayList(favPhoneMap.values()), sortOptionType));
    }

    private void getFavoritesPhoneList() {
        List<Phone> favPhoneList = favoritesPhoneInteractor.getFavoritesPhonesList();
        favPhoneMap.clear();
        if (favPhoneList != null && !favPhoneList.isEmpty()) {
            for (Phone phone : favPhoneList) {
                PhoneDataModel phoneDataModel = phoneDataModelMapper.transform(phone);
                if (phoneDataModel != null) {
                    favPhoneMap.put(phone.getId(), phoneDataModel);
                }
            }
        }
        showPhoneListInView();
    }

    public void removeItemFromList(PhoneDataModel phoneDataModel) {
        if (phoneDataModel != null) {
            if (favPhoneMap.containsKey(phoneDataModel.getId())) {
                favPhoneMap.remove(phoneDataModel.getId());
                List<Phone> phoneList = phoneDataModelMapper.transformPhoneDataModelList(Lists.newArrayList(favPhoneMap.values()));
                favoritesPhoneInteractor.storeFavoritesPhonesList(phoneList);
            }

            if (favPhoneMap.isEmpty()) {
                favoriteListScreen.showNoFavoritesMessage();
            } else {
                favoriteListScreen.hideErrorMessage();
            }
        }
    }

}
