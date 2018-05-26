package com.kumar.ranjan.mobilephone.di.modules;

import com.kumar.ranjan.mobilephone.di.PerActivity;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity activity() {
        return activity;
    }
}
