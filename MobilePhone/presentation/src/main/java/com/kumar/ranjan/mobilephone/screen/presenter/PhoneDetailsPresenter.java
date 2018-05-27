package com.kumar.ranjan.mobilephone.screen.presenter;

import com.kumar.ranjan.mobilephone.di.PerActivity;
import com.kumar.ranjan.mobilephone.domain.Image;
import com.kumar.ranjan.mobilephone.domain.exception.ErrorBundle;
import com.kumar.ranjan.mobilephone.domain.exception.ErrorBundleImpl;
import com.kumar.ranjan.mobilephone.domain.interactor.DefaultObserver;
import com.kumar.ranjan.mobilephone.domain.interactor.GetImageList;
import com.kumar.ranjan.mobilephone.exception.ErrorMessageFactory;
import com.kumar.ranjan.mobilephone.mapper.ImageDataModelMapper;
import com.kumar.ranjan.mobilephone.model.ImageDataModel;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneDetailsScreen;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class PhoneDetailsPresenter {

    private PhoneDetailsScreen phoneDetailsScreen;

    private final GetImageList getImageListUseCase;
    private final ImageDataModelMapper imageDataModelMapper;

    @Inject
    public PhoneDetailsPresenter(GetImageList getImageListUseCase,
                                 ImageDataModelMapper imageDataModelMapper) {
        this.getImageListUseCase = getImageListUseCase;
        this.imageDataModelMapper = imageDataModelMapper;
    }

    public void setView(@NonNull PhoneDetailsScreen view) {
        phoneDetailsScreen = view;
    }

    public void destroy() {
        getImageListUseCase.dispose();
        phoneDetailsScreen = null;
    }

    public void initialize(PhoneDataModel phoneDataModel) {
        phoneDetailsScreen.showPhoneDetailsData(phoneDataModel);
        loadImageList(phoneDataModel.getId());
    }

    private void loadImageList(int mobileId) {
        showViewLoading();
        getImageList(mobileId);
    }

    private void showViewLoading() {
        phoneDetailsScreen.showLoading();
    }

    private void hideViewLoading() {
        phoneDetailsScreen.hideLoading();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.prepareErrorMessage(phoneDetailsScreen.context(), errorBundle.getException());
        phoneDetailsScreen.showError(errorMessage);
    }

    private void showImageListInView(List<Image> imageList) {
        List<ImageDataModel> imageDataModelList = imageDataModelMapper.transform(imageList);
        phoneDetailsScreen.displayImageList(imageDataModelList);
    }

    private void getImageList(int mobileId) {
        GetImageList.Params params = GetImageList.Params.forPhone(mobileId);
        getImageListUseCase.execute(new PhoneDetailsPresenter.ImageListObserver(), params);
    }

    private final class ImageListObserver extends DefaultObserver<List<Image>> {

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
        public void onNext(List<Image> imageList) {
            showImageListInView(imageList);
        }
    }
}
