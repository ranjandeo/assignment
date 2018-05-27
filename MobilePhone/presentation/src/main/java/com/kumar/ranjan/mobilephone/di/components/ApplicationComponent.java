package com.kumar.ranjan.mobilephone.di.components;

import com.kumar.ranjan.mobilephone.di.modules.ApplicationModule;
import com.kumar.ranjan.mobilephone.domain.executor.PostExecutionThread;
import com.kumar.ranjan.mobilephone.domain.executor.ThreadExecutor;
import com.kumar.ranjan.mobilephone.domain.repository.FavoritesRepository;
import com.kumar.ranjan.mobilephone.domain.repository.NetworkRepository;
import com.kumar.ranjan.mobilephone.screen.activity.BaseAppCompatActivity;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseAppCompatActivity baseActivity);

    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    NetworkRepository networkRepository();

    FavoritesRepository favoritesRepository();
}
