package com.kumar.ranjan.mobilephone.data.entity.mapper;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.data.entity.PhoneEntity;
import com.kumar.ranjan.mobilephone.domain.Phone;

import java.util.List;

import javax.inject.Inject;

public class PhoneEntityMapper {

    @Inject
    public PhoneEntityMapper() {

    }

    /**
     * Transform an object of PhoneEntity into Phone
     * @param phoneEntity
     * @return Phone object
     */
    public Phone transform(PhoneEntity phoneEntity) {
        Phone phone = new Phone();

        if (phoneEntity != null) {
            phone.setId(phoneEntity.getId());
            phone.setName(phoneEntity.getName());
            phone.setBrand(phoneEntity.getBrand());
            phone.setDescription(phoneEntity.getDescription());
            phone.setThumbImageURL(phoneEntity.getThumbImageURL());
            phone.setPrice(phoneEntity.getPrice());
            phone.setRating(phoneEntity.getRating());
        }

        return phone;
    }

    /**
     * Transform a list of PhoneEntity into list of Phone
     * @param phoneEntityList
     * @return list of Phone objects
     */
    public List<Phone> transform(List<PhoneEntity> phoneEntityList) {
        List<Phone> phoneList = Lists.newArrayList();

        if (phoneEntityList != null && !phoneEntityList.isEmpty()) {
            for (PhoneEntity phoneEntity : phoneEntityList) {
                phoneList.add(transform(phoneEntity));
            }
        }

        return phoneList;
    }

}
