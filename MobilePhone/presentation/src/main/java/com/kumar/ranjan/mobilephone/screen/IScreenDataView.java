package com.kumar.ranjan.mobilephone.screen;

import android.content.Context;

public interface IScreenDataView {
  void showLoading();

  void hideLoading();

  void showError(String message);

  Context context();
}
