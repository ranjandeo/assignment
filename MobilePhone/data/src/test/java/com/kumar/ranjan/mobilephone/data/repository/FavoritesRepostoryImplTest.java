package com.kumar.ranjan.mobilephone.data.repository;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.data.preference.FavoritesPrefereceStorage;
import com.kumar.ranjan.mobilephone.data.repository.datasource.PhoneListDataStoreFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoritesRepostoryImplTest {

    @Mock
    PhoneListDataStoreFactory phoneListDataStoreFactory;

    @Mock
    FavoritesPrefereceStorage favoritesPrefereceStorage;

    private FavoritesRepostoryImpl favoritesRepostoryImpl;

    @Before
    public void setUp() {
        when(phoneListDataStoreFactory.createFavoritesPhoneStorage()).thenReturn(favoritesPrefereceStorage);
        favoritesRepostoryImpl = new FavoritesRepostoryImpl(phoneListDataStoreFactory);
    }

    @Test
    public void favoritePhonesList() {
        favoritesRepostoryImpl.favoritePhonesList();

        verify(favoritesPrefereceStorage).retrieveFavoriteList();
    }

    @Test
    public void storeFavoritePhonesList() {
        favoritesRepostoryImpl.storeFavoritePhonesList(Lists.newArrayList());

        verify(favoritesPrefereceStorage).storeFavorites(anyList());
    }
}