package com.kumar.ranjan.mobilephone.data.net;

import com.google.gson.JsonSyntaxException;

import com.kumar.ranjan.mobilephone.data.entity.ImageEntity;
import com.kumar.ranjan.mobilephone.data.entity.PhoneEntity;
import com.kumar.ranjan.mobilephone.data.entity.mapper.JsonMapper;
import com.kumar.ranjan.mobilephone.data.exception.GenericException;
import com.kumar.ranjan.mobilephone.data.exception.JsonException;
import com.kumar.ranjan.mobilephone.data.exception.NetworkConnectionException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;

public class ApiServiceImpl implements ApiService {
    private final Context context;
    private final JsonMapper jsonMapper;

    public ApiServiceImpl(Context context, JsonMapper jsonMapper) {
        if (context == null || jsonMapper == null) {
            throw new IllegalArgumentException("Context or JsonMapper is null.");
        }
        this.context = context.getApplicationContext();
        this.jsonMapper = jsonMapper;
    }

    @Override
    public Observable<List<PhoneEntity>> phoneEntityList() {
        return Observable.create(phoneListEmitter -> {
            if (isConnectedToInternet()) {
                try {
                    String phoneListResponse = ApiConnection.createGET(PHONE_LIST_BASE_URL).requestSyncCall();
                    if (phoneListResponse != null) {
                        phoneListEmitter.onNext(jsonMapper.transformPhoneEntityList(phoneListResponse));
                        phoneListEmitter.onComplete();
                    } else {
                        phoneListEmitter.onError(new NetworkConnectionException());
                    }
                } catch (JsonSyntaxException e) {
                    phoneListEmitter.onError(new JsonException(e.getCause()));
                } catch (MalformedURLException e) {
                    phoneListEmitter.onError(new GenericException(e.getCause()));
                }
            } else {
                phoneListEmitter.onError(new NetworkConnectionException());
            }
        });
    }

    @Override
    public Observable<List<ImageEntity>> imageEntityList(int mobileId) {
        return Observable.create(imageListEmitter -> {
            if (isConnectedToInternet()) {
                try {
                    String imageListResponse = ApiConnection.createGET(getImageListUrl(mobileId)).requestSyncCall();
                    if (imageListResponse != null) {
                        imageListEmitter.onNext(jsonMapper.transformImageEntityList(imageListResponse));
                        imageListEmitter.onComplete();
                    } else {
                        imageListEmitter.onError(new NetworkConnectionException());
                    }
                } catch (JsonSyntaxException e) {
                    imageListEmitter.onError(new JsonException(e.getCause()));
                } catch (MalformedURLException e) {
                    imageListEmitter.onError(new GenericException(e.getCause()));
                }
            } else {
                imageListEmitter.onError(new NetworkConnectionException());
            }
        });
    }

    private String getImageListUrl(int mobileId) {
        return IMAGE_LIST_BASE_URL.replace("{mobile_id}", String.valueOf(mobileId));
    }

    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
