package com.kumar.ranjan.mobilephone.screen.fragment;

import com.kumar.ranjan.mobilephone.model.ImageDataModel;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.IScreenDataView;

import java.util.List;

public interface PhoneDetailsScreen extends IScreenDataView {

    void displayImageList(List<ImageDataModel> imageDataModelList);

    void showPhoneDetailsData(PhoneDataModel phoneDataModel);
}
