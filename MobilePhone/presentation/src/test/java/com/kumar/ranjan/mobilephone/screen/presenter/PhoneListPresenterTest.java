package com.kumar.ranjan.mobilephone.screen.presenter;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.domain.interactor.FavoritesPhoneInteractor;
import com.kumar.ranjan.mobilephone.domain.interactor.GetPhoneList;
import com.kumar.ranjan.mobilephone.mapper.PhoneDataModelMapper;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListScreen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhoneListPresenterTest {

    private PhoneListPresenter phoneListPresenter;

    @Mock
    GetPhoneList getPhoneListUseCase;

    @Mock
    FavoritesPhoneInteractor favoritesPhoneInteractor;

    @Mock
    PhoneDataModelMapper phoneDataModelMapper;

    @Mock
    PhoneListScreen phoneListScreen;

    @Before
    public void setUp() {
        when(phoneDataModelMapper.transform(createPhoneList())).thenReturn(createPhoneDataModelList());
        when(phoneDataModelMapper.transform(any(Phone.class))).thenReturn(createPhoneDataModelList().get(0));
        when(favoritesPhoneInteractor.getFavoritesPhonesList()).thenReturn(createPhoneList());
        phoneListPresenter = new PhoneListPresenter(getPhoneListUseCase, favoritesPhoneInteractor, phoneDataModelMapper);
        phoneListPresenter.setView(phoneListScreen);
    }

    @Test
    public void destroy() {
        phoneListPresenter.destroy();

        verify(getPhoneListUseCase).dispose();
    }

    @Test
    public void initialize() {
        phoneListPresenter.initialize();

        verify(phoneListScreen).showLoading();
        verify(getPhoneListUseCase).execute(any(DisposableObserver.class), any(Void.class));
    }

    @Test
    public void onPhoneListItemClicked() {
        phoneListPresenter.onPhoneListItemClicked(new PhoneDataModel());

        verify(phoneListScreen).showPhoneDetails(any(PhoneDataModel.class));
    }

    @Test
    public void onFavoriteButtonClicked() {
        phoneListPresenter.onFavoriteButtonClicked(new PhoneDataModel());

        verify(phoneListScreen).onMarkedAsFavorite(any(PhoneDataModel.class));
    }

    @Test
    public void applySorting() {
        phoneListPresenter.applySorting(SortOptionType.PRICE_LOW_TO_HIGH);

        verify(phoneListScreen).displayPhoneList(anyList());
    }

    @Test
    public void onRemedFromFavorite() {
        phoneListPresenter.onRemedFromFavorite(new PhoneDataModel(1, "name", "brand", "description", "url", 109.66, 4.1, false));

        verify(phoneListScreen).displayPhoneList(anyList());
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