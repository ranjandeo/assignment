package com.kumar.ranjan.mobilephone.data.net;

import android.support.annotation.Nullable;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class ApiConnection implements Callable<String> {

  private static final String CONTENT_TYPE = "Content-Type";
  private static final String CONTENT_TYPE_VALUE = "application/json; charset=utf-8";

  private URL url;
  private String response;

  private ApiConnection(String url) throws MalformedURLException {
    this.url = new URL(url);
  }

  static ApiConnection createGET(String url) throws MalformedURLException {
    return new ApiConnection(url);
  }

  @Nullable
  String requestSyncCall() {
    connectToApi();
    return response;
  }

  private void connectToApi() {
    OkHttpClient okHttpClient = this.createClient();
    Request request = new Request.Builder()
        .url(url)
        .addHeader(CONTENT_TYPE, CONTENT_TYPE_VALUE)
        .get()
        .build();

    try {
      response = okHttpClient.newCall(request).execute().body().string();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private OkHttpClient createClient() {
    final OkHttpClient okHttpClient = new OkHttpClient();
    okHttpClient.setReadTimeout(60000, TimeUnit.MILLISECONDS);
    okHttpClient.setConnectTimeout(60000, TimeUnit.MILLISECONDS);

    return okHttpClient;
  }

  @Override
  public String call() {
    return requestSyncCall();
  }
}