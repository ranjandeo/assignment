package com.kumar.ranjan.mobilephone.data.repository.datasource;

import com.kumar.ranjan.mobilephone.data.net.ApiService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class NetworkDataStoreImplTest {

    private NetworkDataStore networkDataStore;

    @Mock
    private ApiService apiService;

    @Before
    public void setUp() {
        networkDataStore = new NetworkDataStoreImpl(apiService);
    }

    @Test
    public void testGetPhoneEntityList() {
        networkDataStore.phoneEntityList();
        verify(apiService).phoneEntityList();
    }

    @Test
    public void testGetImageEntityList() {
        networkDataStore.imageEntityList(1);
        verify(apiService).imageEntityList(anyInt());
    }
}