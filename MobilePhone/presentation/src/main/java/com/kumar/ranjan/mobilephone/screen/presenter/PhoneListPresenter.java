package com.kumar.ranjan.mobilephone.screen.presenter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.kumar.ranjan.mobilephone.di.PerActivity;
import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.exception.ErrorBundle;
import com.kumar.ranjan.mobilephone.domain.exception.ErrorBundleImpl;
import com.kumar.ranjan.mobilephone.domain.interactor.DefaultObserver;
import com.kumar.ranjan.mobilephone.domain.interactor.FavoritesPhoneInteractor;
import com.kumar.ranjan.mobilephone.domain.interactor.GetPhoneList;
import com.kumar.ranjan.mobilephone.exception.ErrorMessageFactory;
import com.kumar.ranjan.mobilephone.helper.PhoneListSorter;
import com.kumar.ranjan.mobilephone.mapper.PhoneDataModelMapper;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListScreen;

import android.support.annotation.NonNull;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

@PerActivity
public class PhoneListPresenter {
    private PhoneListScreen phoneListScreen;

    private final GetPhoneList getPhoneListUseCase;
    private final FavoritesPhoneInteractor favoritesPhoneInteractor;
    private final PhoneDataModelMapper phoneDataModelMapper;
    private final List<PhoneDataModel> phoneDataModelList;
    private final Map<Integer, PhoneDataModel> favPhoneMap;

    @Inject
    public PhoneListPresenter(GetPhoneList getPhoneListUseCase,
                              FavoritesPhoneInteractor favoritesPhoneInteractor,
                              PhoneDataModelMapper phoneDataModelMapper) {
        this.getPhoneListUseCase = getPhoneListUseCase;
        this.favoritesPhoneInteractor = favoritesPhoneInteractor;
        this.phoneDataModelMapper = phoneDataModelMapper;
        phoneDataModelList = Lists.newArrayList();
        favPhoneMap = Maps.newHashMap();
        getFavoritesPhoneList();
    }

    public void setView(@NonNull PhoneListScreen view) {
        phoneListScreen = view;
    }

    public void destroy() {
        getPhoneListUseCase.dispose();
        phoneListScreen = null;
    }

    public void initialize() {
        loadPhoneList();
    }

    private void loadPhoneList() {
        showViewLoading();
        getPhoneList();
    }

    public void onPhoneListItemClicked(PhoneDataModel phoneDataModel) {
        phoneListScreen.showPhoneDetails(phoneDataModel);
    }

    public void onFavoriteButtonClicked(PhoneDataModel phoneDataModel) {
        if (phoneDataModel.isFavorite()) {
            if (!favPhoneMap.containsKey(phoneDataModel.getId())) {
                favPhoneMap.put(phoneDataModel.getId(), phoneDataModel);
            }
        } else {
            if (favPhoneMap.containsKey(phoneDataModel.getId())) {
                favPhoneMap.remove(phoneDataModel.getId());
            }
        }

        //store the updated list back to the preference
        storeFavoritePhonesInStorage();

        phoneListScreen.onMarkedAsFavorite(phoneDataModel);
    }

    private void storeFavoritePhonesInStorage() {
        List<Phone> favPhoneList = phoneDataModelMapper.transformPhoneDataModelList(Lists.newArrayList(favPhoneMap.values()));
        favoritesPhoneInteractor.storeFavoritesPhonesList(favPhoneList);
    }

    private void showViewLoading() {
        phoneListScreen.showLoading();
    }

    private void hideViewLoading() {
        phoneListScreen.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.prepareErrorMessage(phoneListScreen.context(), errorBundle.getException());
        phoneListScreen.showError(errorMessage);
    }

    private void showPhoneListInView(List<Phone> phoneList) {
        phoneDataModelList.clear();
        phoneDataModelList.addAll(phoneDataModelMapper.transform(phoneList));
        updatePhoneListBasedOnFavMap();
        phoneListScreen.displayPhoneList(phoneDataModelList);
    }

    private void getPhoneList() {
        getPhoneListUseCase.execute(new PhoneListObserver(), null);
    }

    public void applySorting(SortOptionType sortOptionType) {
        PhoneListSorter sorter = new PhoneListSorter();
        phoneListScreen.displayPhoneList(sorter.sort(phoneDataModelList, sortOptionType));
    }

    private final class PhoneListObserver extends DefaultObserver<List<Phone>> {

        @Override
        public void onComplete() {
            hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            hideViewLoading();
            showErrorMessage(new ErrorBundleImpl((Exception) e));
        }

        @Override
        public void onNext(List<Phone> phoneList) {
            showPhoneListInView(phoneList);
        }
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
    }

    private void updatePhoneListBasedOnFavMap() {
        if (!favPhoneMap.isEmpty() && !phoneDataModelList.isEmpty()) {
            for (PhoneDataModel phoneDataModel : phoneDataModelList) {
                if (favPhoneMap.containsKey(phoneDataModel.getId())) {
                    phoneDataModel.setFavorite(true);
                } else {
                    phoneDataModel.setFavorite(false);
                }
            }
        }
    }

    public void onRemedFromFavorite(PhoneDataModel phoneDataModel) {
        if (phoneDataModel != null) {
            if (favPhoneMap.containsKey(phoneDataModel.getId())) {
                favPhoneMap.remove(phoneDataModel.getId());

                // update the local storage
                storeFavoritePhonesInStorage();

                // update the reccycler view
                updatePhoneListBasedOnFavMap();
                phoneListScreen.displayPhoneList(phoneDataModelList);
            }
        }
    }
}
