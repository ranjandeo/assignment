package com.kumar.ranjan.mobilephone.screen.activity;

import com.kumar.ranjan.mobilephone.MobilePhoneBuyerGuideApplication;
import com.kumar.ranjan.mobilephone.di.components.ApplicationComponent;
import com.kumar.ranjan.mobilephone.di.modules.ActivityModule;
import com.kumar.ranjan.mobilephone.router.ActivityRouter;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

public class BaseAppCompatActivity extends AppCompatActivity {
    @Inject
    ActivityRouter activityRouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((MobilePhoneBuyerGuideApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}
