package com.kumar.ranjan.mobilephone.screen.activity;

import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.di.HasComponent;
import com.kumar.ranjan.mobilephone.di.components.DaggerPhoneDetailsComponent;
import com.kumar.ranjan.mobilephone.di.components.PhoneDetailsComponent;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneDetailsFragment;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

public class PhoneDetailsActivity extends BaseAppCompatActivity implements HasComponent<PhoneDetailsComponent> {

    private PhoneDetailsComponent phoneDetailsComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_details);

        initializeInjector();
        Bundle extras = getIntent().getExtras();
        if (savedInstanceState == null) {
            PhoneDetailsFragment fragment = new PhoneDetailsFragment();
            addFragment(R.id.fragmentContainer, fragment);
            fragment.setArguments(extras);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Display custom title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.phone_details);

        // Display the back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initializeInjector() {
        phoneDetailsComponent = DaggerPhoneDetailsComponent.builder()
                                                           .applicationComponent(getApplicationComponent())
                                                           .activityModule(getActivityModule())
                                                           .build();
    }

    @Override
    public PhoneDetailsComponent getComponent() {
        return phoneDetailsComponent;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
