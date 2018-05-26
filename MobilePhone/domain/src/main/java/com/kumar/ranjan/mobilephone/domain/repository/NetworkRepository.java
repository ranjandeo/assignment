package com.kumar.ranjan.mobilephone.domain.repository;

import com.kumar.ranjan.mobilephone.domain.Image;
import com.kumar.ranjan.mobilephone.domain.Phone;

import java.util.List;

import io.reactivex.Observable;

public interface NetworkRepository {

    Observable<List<Phone>> phoneList();

    Observable<List<Image>> imageList(final int mobileId);
}
