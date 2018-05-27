package com.kumar.ranjan.mobilephone.screen.presenter;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.interactor.FavoritesPhoneInteractor;
import com.kumar.ranjan.mobilephone.mapper.PhoneDataModelMapper;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;
import com.kumar.ranjan.mobilephone.screen.fragment.FavoriteListScreen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoritePhoneListPresenterTest {

    @Mock
    FavoritesPhoneInteractor favoritesPhoneInteractor;

    @Mock
    PhoneDataModelMapper phoneDataModelMapper;

    @Mock
    FavoriteListScreen favoriteListScreen;

    private FavoritePhoneListPresenter presenter;

    @Before
    public void setUp() {
        when(favoritesPhoneInteractor.getFavoritesPhonesList()).thenReturn(createPhoneList());
        when(phoneDataModelMapper.transform(createPhoneList())).thenReturn(createPhoneDataModelList());

        when(phoneDataModelMapper.transform(any(Phone.class))).thenReturn(createPhoneDataModelList().get(0));
        presenter = new FavoritePhoneListPresenter(favoritesPhoneInteractor, phoneDataModelMapper);
        presenter.setView(favoriteListScreen);
    }

    @Test
    public void initialize() {
        presenter.initialize();

        verify(favoriteListScreen).displayFavoriteList(anyList());
        verify(favoriteListScreen).hideErrorMessage();
    }

    @Test
    public void onPhoneListItemClicked() {
        presenter.onPhoneListItemClicked(createPhoneDataModelList().get(0));

        verify(favoriteListScreen).showPhoneDetails(any(PhoneDataModel.class));
    }

    @Test
    public void applySorting() {
        presenter.applySorting(SortOptionType.RATING_5_To_1);

        verify(favoriteListScreen).displayFavoriteList(anyList());
    }

    @Test
    public void removeItemFromList() {
        presenter.removeItemFromList(createPhoneDataModelList().get(0));

        verify(favoriteListScreen).showNoFavoritesMessage();
    }

    private List<Phone> createPhoneList() {
        List<Phone> list = Lists.newArrayList();
        list.add(new Phone(1, "name", "brand", "description", "url", 109.66, 4.1, false));
        list.add(new Phone(2, "name", "brand", "description", "url", 99.76, 4.9, false));
        list.add(new Phone(3, "name", "brand", "description", "url", 309.87, 2.5, false));
        list.add(new Phone(4, "name", "brand", "description", "url", 209.69, 3.6, false));
        return list;
    }

    private List<PhoneDataModel> createPhoneDataModelList() {
        List<PhoneDataModel> list = Lists.newArrayList();
        list.add(new PhoneDataModel(1, "name", "brand", "description", "url", 109.66, 4.1, false));
        list.add(new PhoneDataModel(2, "name", "brand", "description", "url", 99.76, 4.9, false));
        list.add(new PhoneDataModel(3, "name", "brand", "description", "url", 309.87, 2.5, false));
        list.add(new PhoneDataModel(4, "name", "brand", "description", "url", 209.69, 3.6, false));
        return list;
    }

}