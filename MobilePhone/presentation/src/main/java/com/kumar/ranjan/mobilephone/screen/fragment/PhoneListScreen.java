package com.kumar.ranjan.mobilephone.screen.fragment;

import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.IScreenDataView;

import java.util.List;

public interface PhoneListScreen extends IScreenDataView {

    void displayPhoneList(List<PhoneDataModel> phoneDataModelList);

    void showPhoneDetails(PhoneDataModel phoneDataModel);
}