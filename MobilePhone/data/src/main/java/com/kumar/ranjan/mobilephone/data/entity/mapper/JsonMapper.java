package com.kumar.ranjan.mobilephone.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import com.kumar.ranjan.mobilephone.data.entity.ImageEntity;
import com.kumar.ranjan.mobilephone.data.entity.PhoneEntity;
import com.kumar.ranjan.mobilephone.domain.Phone;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

public class JsonMapper {

    private Gson gson;

    @Inject
    public JsonMapper() {
        gson = new Gson();
    }

    public List<PhoneEntity> transformPhoneEntityList(String jsonResponse) throws JsonSyntaxException {
        final Type phoneEntityListType = new TypeToken<List<PhoneEntity>>() {}.getType();
        return gson.fromJson(jsonResponse, phoneEntityListType);
    }

    public List<ImageEntity> transformImageEntityList(String jsonResponse) throws JsonSyntaxException {
        final Type imageEntityListType = new TypeToken<List<ImageEntity>>() {}.getType();
        return gson.fromJson(jsonResponse, imageEntityListType);
    }

    public List<Phone> transformPhoneList(String jsonResponse) throws JsonSyntaxException {
        final Type phoneListType = new TypeToken<List<Phone>>() {}.getType();
        return gson.fromJson(jsonResponse, phoneListType);
    }

    public String transformPhoneListToJson(List<Phone> phoneList) throws JsonSyntaxException {
        final Type phoneListType = new TypeToken<List<Phone>>() {}.getType();
        return gson.toJson(phoneList, phoneListType);
    }
}
