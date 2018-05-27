package com.kumar.ranjan.mobilephone.screen.presenter;

import com.kumar.ranjan.mobilephone.domain.interactor.GetImageList;
import com.kumar.ranjan.mobilephone.mapper.ImageDataModelMapper;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneDetailsScreen;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PhoneDetailsPresenterTest {

    @Mock
    GetImageList getImageListUseCase;

    @Mock
    ImageDataModelMapper imageDataModelMapper;

    @Mock
    PhoneDetailsScreen phoneDetailsScreen;

    private PhoneDetailsPresenter presenter;

    @Before
    public void setUp() {
        presenter = new PhoneDetailsPresenter(getImageListUseCase, imageDataModelMapper);
        presenter.setView(phoneDetailsScreen);
    }

    @Test
    public void destroy() {
        presenter.destroy();

        verify(getImageListUseCase).dispose();
    }

    @Test
    public void initialize() {
        presenter.initialize(new PhoneDataModel(1, "", "", "", "", 100.0, 4.7, false));

        verify(phoneDetailsScreen).showPhoneDetailsData(any(PhoneDataModel.class));
        verify(phoneDetailsScreen).showLoading();
    }
}