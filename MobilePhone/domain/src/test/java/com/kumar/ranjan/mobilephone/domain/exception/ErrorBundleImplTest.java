package com.kumar.ranjan.mobilephone.domain.exception;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ErrorBundleImplTest {

    private ErrorBundleImpl errorBundle;

    private Exception mockException = mock(Exception.class);

    @Before
    public void setUp() {
        errorBundle = new ErrorBundleImpl(mockException);
    }

    @Test
    public void testGetException() {
        assertThat(errorBundle.getException(), is(notNullValue()));
    }

    @Test
    public void testGetErrorMessage() {
        errorBundle.getErrorMessage();

        verify(mockException).getMessage();
    }
}