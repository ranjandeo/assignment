package com.kumar.ranjan.mobilephone.exception;


import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.data.exception.GenericException;
import com.kumar.ranjan.mobilephone.data.exception.JsonException;
import com.kumar.ranjan.mobilephone.data.exception.NetworkConnectionException;

import android.content.Context;

public class ErrorMessageFactory {

    public static String prepareErrorMessage(Context context, Exception exception) {
        String message = context.getString(R.string.generic_error_message);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.no_network_message);
        } else if (exception instanceof GenericException) {
            message = context.getString(R.string.generic_error_message);
        } else if (exception instanceof JsonException) {
            message = context.getString(R.string.json_error_message);
        }

        return message;
    }
}
