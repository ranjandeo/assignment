package com.kumar.ranjan.mobilephone.di.components;

import com.kumar.ranjan.mobilephone.di.PerActivity;
import com.kumar.ranjan.mobilephone.di.modules.ActivityModule;
import com.kumar.ranjan.mobilephone.di.modules.PhoneDetailsModule;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneDetailsFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PhoneDetailsModule.class})
public interface PhoneDetailsComponent extends ActivityComponent {
    void inject(PhoneDetailsFragment phoneDetailsFragment);
}