package com.kumar.ranjan.mobilephone.router;

import com.kumar.ranjan.mobilephone.model.PhoneDataModel;

import org.junit.Before;
import org.junit.Test;

import android.content.Context;
import android.content.Intent;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ActivityRouterTest {

    private ActivityRouter activityRouter;

    @Before
    public void setUp() {
        activityRouter = new ActivityRouter();
    }

    @Test
    public void testGoToPhoneDetailsPage() {
        // Arrange
        Context context = mock(Context.class);
        PhoneDataModel phoneDataModel = new PhoneDataModel();

        // Act
        activityRouter.goToPhoneDetailsPage(context, phoneDataModel);

        // verify
        verify(context, times(1)).startActivity(any(Intent.class));
    }
}