package com.kumar.ranjan.mobilephone.di.modules;

import com.kumar.ranjan.mobilephone.MobilePhoneBuyerGuideApplication;
import com.kumar.ranjan.mobilephone.data.executor.JobExecutor;
import com.kumar.ranjan.mobilephone.data.repository.FavoritesRepostoryImpl;
import com.kumar.ranjan.mobilephone.data.repository.NetworkRepositoryImpl;
import com.kumar.ranjan.mobilephone.di.UIThread;
import com.kumar.ranjan.mobilephone.domain.executor.PostExecutionThread;
import com.kumar.ranjan.mobilephone.domain.executor.ThreadExecutor;
import com.kumar.ranjan.mobilephone.domain.repository.FavoritesRepository;
import com.kumar.ranjan.mobilephone.domain.repository.NetworkRepository;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private final MobilePhoneBuyerGuideApplication application;

    public ApplicationModule(MobilePhoneBuyerGuideApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    NetworkRepository provideNetworkRepository(NetworkRepositoryImpl networkRepository) {
        return networkRepository;
    }

    @Provides
    @Singleton
    FavoritesRepository provideFavoritesRepository(FavoritesRepostoryImpl favoritesRepository) {
        return favoritesRepository;
    }
}
