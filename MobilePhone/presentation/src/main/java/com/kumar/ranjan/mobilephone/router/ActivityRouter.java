package com.kumar.ranjan.mobilephone.router;

import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.activity.PhoneDetailsActivity;

import org.parceler.Parcels;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ActivityRouter {
    public static String INTENT_PHONE_DATA = "phone_data_model";

    @Inject
    public ActivityRouter() {
    }

    public void goToPhoneDetailsPage(Context context, PhoneDataModel phoneDataModel) {
        if (context != null) {
            Intent intentPhoneDetails = new Intent(context, PhoneDetailsActivity.class);
            Parcelable parcelablePhoneDataModel = Parcels.wrap(phoneDataModel);
            intentPhoneDetails.putExtra(INTENT_PHONE_DATA, parcelablePhoneDataModel);
            context.startActivity(intentPhoneDetails);
        }
    }
}
