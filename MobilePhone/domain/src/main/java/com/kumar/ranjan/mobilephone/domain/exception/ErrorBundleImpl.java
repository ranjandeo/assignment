package com.kumar.ranjan.mobilephone.domain.exception;

public class ErrorBundleImpl implements ErrorBundle {

  private static final String DEFAULT_ERROR_MSG = "Technical error";

  private final Exception exception;

  public ErrorBundleImpl(Exception exception) {
    this.exception = exception;
  }

  @Override
  public Exception getException() {
    return exception;
  }

  @Override
  public String getErrorMessage() {
    return exception != null ? exception.getMessage() : DEFAULT_ERROR_MSG;
  }
}
