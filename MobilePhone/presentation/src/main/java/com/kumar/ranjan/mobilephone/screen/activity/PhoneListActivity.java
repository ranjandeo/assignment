package com.kumar.ranjan.mobilephone.screen.activity;

import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.di.HasComponent;
import com.kumar.ranjan.mobilephone.di.components.DaggerPhoneListComponent;
import com.kumar.ranjan.mobilephone.di.components.PhoneListComponent;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListFragment;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListItemClickListener;

import android.os.Bundle;

public class PhoneListActivity extends BaseAppCompatActivity implements HasComponent<PhoneListComponent>,
        PhoneListItemClickListener {

    private PhoneListComponent phoneListComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);

        initializeInjector();
        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new PhoneListFragment());
        }
    }

    private void initializeInjector() {
        phoneListComponent = DaggerPhoneListComponent.builder()
                                                .applicationComponent(getApplicationComponent())
                                                .activityModule(getActivityModule())
                                                .build();
    }

    @Override
    public PhoneListComponent getComponent() {
        return phoneListComponent;
    }

    @Override
    public void onPhoneItemClicked(PhoneDataModel phoneDataModel) {
        activityRouter.goToPhoneDetailsPage(this, phoneDataModel);
    }
}

