package com.kumar.ranjan.mobilephone;

import com.kumar.ranjan.mobilephone.di.components.ApplicationComponent;
import com.kumar.ranjan.mobilephone.di.components.DaggerApplicationComponent;
import com.kumar.ranjan.mobilephone.di.modules.ApplicationModule;

import android.app.Application;

public class MobilePhoneBuyerGuideApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                                                         .applicationModule(new ApplicationModule(this))
                                                         .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
