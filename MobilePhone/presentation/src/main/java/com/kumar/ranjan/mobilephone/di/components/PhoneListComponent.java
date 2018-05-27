package com.kumar.ranjan.mobilephone.di.components;

import com.kumar.ranjan.mobilephone.di.PerActivity;
import com.kumar.ranjan.mobilephone.di.modules.ActivityModule;
import com.kumar.ranjan.mobilephone.di.modules.PhoneListModule;
import com.kumar.ranjan.mobilephone.screen.fragment.FavoritePhoneListFragment;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, PhoneListModule.class})
public interface PhoneListComponent extends ActivityComponent {
    void inject(PhoneListFragment phoneListFragment);
    void inject(FavoritePhoneListFragment favoritePhoneListFragment);
}
