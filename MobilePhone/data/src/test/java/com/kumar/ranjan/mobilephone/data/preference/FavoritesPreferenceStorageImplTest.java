package com.kumar.ranjan.mobilephone.data.preference;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.domain.Phone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoritesPreferenceStorageImplTest {

    private FavoritesPreferenceStorageImpl favoritesPreferenceStorage;

    @Mock
    Context context;

    @Mock
    SharedPreferences mockSharedPreferences;

    @Mock
    SharedPreferences.Editor mockEditor;

    @Before
    public void setUp() {
        when(context.getSharedPreferences(anyString(), anyInt())).thenReturn(mockSharedPreferences);
        when(mockSharedPreferences.edit()).thenReturn(mockEditor);
        favoritesPreferenceStorage = new FavoritesPreferenceStorageImpl(context);
    }

    @Test
    public void storeFavorites_WithEmptyList() {
        favoritesPreferenceStorage.storeFavorites(Lists.newArrayList());

        verify(mockEditor).clear();
        verify(mockEditor).apply();
    }

    @Test
    public void storeFavorites() {
        List<Phone> phoneList = Lists.newArrayList();
        phoneList.add(new Phone(1, "Name", "Brand", "Description", "url", 100.00, 4.6, true));
        favoritesPreferenceStorage.storeFavorites(phoneList);

        verify(mockEditor).putString(anyString(), anyString());
        verify(mockEditor).apply();
    }

    @Test
    public void retrieveFavoriteList() {
        when(mockSharedPreferences.getString(anyString(), anyString())).thenReturn("");
        favoritesPreferenceStorage.retrieveFavoriteList();

        verify(mockSharedPreferences).getString(anyString(), anyString());
    }
}