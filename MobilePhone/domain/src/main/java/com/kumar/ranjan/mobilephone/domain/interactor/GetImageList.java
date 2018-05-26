package com.kumar.ranjan.mobilephone.domain.interactor;

import com.kumar.ranjan.mobilephone.domain.Image;
import com.kumar.ranjan.mobilephone.domain.executor.PostExecutionThread;
import com.kumar.ranjan.mobilephone.domain.executor.ThreadExecutor;
import com.kumar.ranjan.mobilephone.domain.repository.NetworkRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetImageList extends UseCase<List<Image>, GetImageList.Params> {

    private final NetworkRepository networkRepository;

    @Inject
    public GetImageList(NetworkRepository networkRepository,
                         ThreadExecutor threadExecutor,
                         PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.networkRepository = networkRepository;
    }

    @Override
    Observable<List<Image>> buildUseCaseObservable(Params params) {
        return networkRepository.imageList(params.mobileId);
    }

    public static final class Params {
        private final int mobileId;

        private Params(int mobileId) {
            this.mobileId = mobileId;
        }

        public static Params forPhone(int mobileId) {
            return new Params(mobileId);
        }
    }
}
