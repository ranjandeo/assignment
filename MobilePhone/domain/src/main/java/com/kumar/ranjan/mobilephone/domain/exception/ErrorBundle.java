package com.kumar.ranjan.mobilephone.domain.exception;

public interface ErrorBundle {
  Exception getException();

  String getErrorMessage();
}
