package com.kumar.ranjan.mobilephone.di.components;


import com.kumar.ranjan.mobilephone.di.PerActivity;
import com.kumar.ranjan.mobilephone.di.modules.ActivityModule;

import android.app.Activity;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class) interface ActivityComponent {
    Activity activity();
}
