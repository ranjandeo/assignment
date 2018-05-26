package com.kumar.ranjan.mobilephone.exception;

import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.data.exception.GenericException;
import com.kumar.ranjan.mobilephone.data.exception.JsonException;
import com.kumar.ranjan.mobilephone.data.exception.NetworkConnectionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ErrorMessageFactoryTest {

    @Mock
    Context context;

    @Test
    public void prepareErrorMessage() {
        when(context.getString(R.string.generic_error_message)).thenReturn("Generic error!");
        when(context.getString(R.string.no_network_message)).thenReturn("No network error!");
        when(context.getString(R.string.json_error_message)).thenReturn("JSON error!");

        String genericErrorMessage = ErrorMessageFactory.prepareErrorMessage(context, new GenericException());
        String networkErrorMessage = ErrorMessageFactory.prepareErrorMessage(context, new NetworkConnectionException());
        String JsonErrorMessage = ErrorMessageFactory.prepareErrorMessage(context, new JsonException());

        assertEquals(genericErrorMessage, "Generic error!");
        assertEquals(networkErrorMessage, "No network error!");
        assertEquals(JsonErrorMessage, "JSON error!");
    }
}