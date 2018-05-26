package com.kumar.ranjan.mobilephone.domain.interactor;


import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.executor.PostExecutionThread;
import com.kumar.ranjan.mobilephone.domain.executor.ThreadExecutor;
import com.kumar.ranjan.mobilephone.domain.repository.NetworkRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class GetPhoneList extends UseCase<List<Phone>, Void> {

    private final NetworkRepository networkRepository;

    @Inject
    public GetPhoneList(NetworkRepository networkRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.networkRepository = networkRepository;
    }

    @Override
    Observable<List<Phone>> buildUseCaseObservable(Void params) {
        return networkRepository.phoneList();
    }
}
