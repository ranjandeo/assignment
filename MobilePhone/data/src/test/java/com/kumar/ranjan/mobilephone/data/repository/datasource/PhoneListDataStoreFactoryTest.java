package com.kumar.ranjan.mobilephone.data.repository.datasource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhoneListDataStoreFactoryTest {

    private PhoneListDataStoreFactory phoneListDataStoreFactory;

    @Mock
    Context context;

    @Before
    public void setUp() {
        when(context.getApplicationContext()).thenReturn(context);
        phoneListDataStoreFactory = new PhoneListDataStoreFactory(context);
    }

    @Test
    public void testCreateClodDataStore() {
        NetworkDataStore networkDataStore = phoneListDataStoreFactory.createCloudDataStore();

        assertThat(networkDataStore, is(notNullValue()));
    }
}