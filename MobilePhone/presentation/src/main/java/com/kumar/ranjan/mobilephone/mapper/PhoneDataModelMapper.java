package com.kumar.ranjan.mobilephone.mapper;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.di.PerActivity;
import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;

import java.util.List;

import javax.inject.Inject;

@PerActivity
public class PhoneDataModelMapper {

    @Inject
    public PhoneDataModelMapper() {}

    /**
     * Transform Phone object to PhoneDataModel object
     * @param phone
     * @return
     */
    public PhoneDataModel transform(Phone phone) {
        PhoneDataModel phoneDataModel = null;

        if (phone != null) {
            phoneDataModel = new PhoneDataModel();
            phoneDataModel.setId(phone.getId());
            phoneDataModel.setName(phone.getName());
            phoneDataModel.setBrand(phone.getBrand());
            phoneDataModel.setDescription(phone.getDescription());
            phoneDataModel.setThumbImageURL(phone.getThumbImageURL());
            phoneDataModel.setPrice(phone.getPrice());
            phoneDataModel.setRating(phone.getRating());
            phoneDataModel.setFavorite(phone.isFavorite());
        }

        return phoneDataModel;
    }

    /**
     * Transform list of Phone objects to list of PhoneDataModel objects
     * @param phoneList
     * @return
     */
    public List<PhoneDataModel> transform(List<Phone> phoneList) {
        List<PhoneDataModel> phoneDataModelList = Lists.newArrayList();

        if (phoneList != null && !phoneList.isEmpty()) {
            for (Phone phone : phoneList) {
                PhoneDataModel phoneDataModel = transform(phone);
                if (phoneDataModel != null) {
                    phoneDataModelList.add(phoneDataModel);
                }
            }
        }

        return phoneDataModelList;
    }

    /**
     * Transform PhoneDataModel object to Phone object
     * @param phoneDataModel
     * @return
     */
    public Phone transform(PhoneDataModel phoneDataModel) {
        Phone phone = null;

        if (phoneDataModel != null) {
            phone = new Phone();
            phone.setId(phoneDataModel.getId());
            phone.setName(phoneDataModel.getName());
            phone.setBrand(phoneDataModel.getBrand());
            phone.setDescription(phoneDataModel.getDescription());
            phone.setThumbImageURL(phoneDataModel.getThumbImageURL());
            phone.setPrice(phoneDataModel.getPrice());
            phone.setRating(phoneDataModel.getRating());
            phone.setFavorite(phoneDataModel.isFavorite());
        }

        return phone;
    }

    /**
     * Transform list of PhoneDataModel objects to list of Phone objects
     * @param phoneDataModels
     * @return
     */
    public List<Phone> transformPhoneDataModelList(List<PhoneDataModel> phoneDataModels) {
        List<Phone> phoneList = Lists.newArrayList();

        if (phoneDataModels != null && !phoneDataModels.isEmpty()) {
            for (PhoneDataModel phoneDataModel : phoneDataModels) {
                Phone phone = transform(phoneDataModel);
                if (phone != null) {
                    phoneList.add(phone);
                }
            }
        }

        return phoneList;
    }

}
