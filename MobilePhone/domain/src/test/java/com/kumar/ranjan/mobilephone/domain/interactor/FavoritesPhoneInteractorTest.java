package com.kumar.ranjan.mobilephone.domain.interactor;

import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.repository.FavoritesRepository;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FavoritesPhoneInteractorTest {

    @Mock
    FavoritesRepository favoritesRepository;

    private FavoritesPhoneInteractor favoritesPhoneInteractor;

    @Before
    public void setUp() {
        favoritesPhoneInteractor = new FavoritesPhoneInteractor(favoritesRepository);
    }

    @Test
    public void getFavoritesPhonesList() {
        favoritesPhoneInteractor.getFavoritesPhonesList();

        verify(favoritesRepository).favoritePhonesList();
    }

    @Test
    public void storeFavoritesPhonesList() {
        favoritesPhoneInteractor.storeFavoritesPhonesList(Lists.<Phone>newArrayList());

        verify(favoritesRepository).storeFavoritePhonesList(anyList());
    }
}