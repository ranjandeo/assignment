package com.kumar.ranjan.mobilephone.screen.presenter;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.di.PerActivity;
import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.exception.ErrorBundle;
import com.kumar.ranjan.mobilephone.domain.exception.ErrorBundleImpl;
import com.kumar.ranjan.mobilephone.domain.interactor.DefaultObserver;
import com.kumar.ranjan.mobilephone.domain.interactor.GetPhoneList;
import com.kumar.ranjan.mobilephone.exception.ErrorMessageFactory;
import com.kumar.ranjan.mobilephone.helper.PhoneListSorter;
import com.kumar.ranjan.mobilephone.mapper.PhoneDataModelMapper;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListScreen;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class PhoneListPresenter {
    private PhoneListScreen phoneListScreen;

    private final GetPhoneList getPhoneListUseCase;
    private final PhoneDataModelMapper phoneDataModelMapper;
    private List<PhoneDataModel> phoneDataModelList;

    @Inject
    public PhoneListPresenter(GetPhoneList getPhoneListUseCase,
                              PhoneDataModelMapper phoneDataModelMapper) {
        this.getPhoneListUseCase = getPhoneListUseCase;
        this.phoneDataModelMapper = phoneDataModelMapper;
        phoneDataModelList = Lists.newArrayList();
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
        getUserList();
    }

    public void onPhoneListItemClicked(PhoneDataModel phoneDataModel) {
        phoneListScreen.showPhoneDetails(phoneDataModel);
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
        phoneListScreen.displayPhoneList(phoneDataModelList);
    }

    private void getUserList() {
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
}
