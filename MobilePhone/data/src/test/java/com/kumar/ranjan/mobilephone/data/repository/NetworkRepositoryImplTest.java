package com.kumar.ranjan.mobilephone.data.repository;

import com.kumar.ranjan.mobilephone.data.entity.ImageEntity;
import com.kumar.ranjan.mobilephone.data.entity.PhoneEntity;
import com.kumar.ranjan.mobilephone.data.entity.mapper.ImageEntityMapper;
import com.kumar.ranjan.mobilephone.data.entity.mapper.PhoneEntityMapper;
import com.kumar.ranjan.mobilephone.data.repository.datasource.NetworkDataStore;
import com.kumar.ranjan.mobilephone.data.repository.datasource.PhoneListDataStoreFactory;
import com.kumar.ranjan.mobilephone.domain.repository.NetworkRepository;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class NetworkRepositoryImplTest {
    private static final int MOBILE_ID = 1;

    private NetworkRepository networkRepository;

    @Mock
    private PhoneEntityMapper phoneEntityMapper;
    @Mock
    private ImageEntityMapper imageEntityMapper;
    @Mock
    private PhoneListDataStoreFactory dataStoreFactory;
    @Mock
    NetworkDataStore networkDataStore;

    @Before
    public void setUp() {
        networkRepository = new NetworkRepositoryImpl(dataStoreFactory, phoneEntityMapper, imageEntityMapper);
    }

    @Test
    public void testGetPhoneList() {
        // Arrange
        List<PhoneEntity> phoneEntityList = Lists.newArrayList();
        phoneEntityList.add(new PhoneEntity());
        given(dataStoreFactory.createCloudDataStore()).willReturn(networkDataStore);
        given(networkDataStore.phoneEntityList()).willReturn(Observable.just(phoneEntityList));

        // Act
        networkRepository.phoneList();

        // Assert
        verify(networkDataStore).phoneEntityList();
    }

    @Test
    public void testGetImageList() {
        // Arrange
        List<ImageEntity> imageEntityList = Lists.newArrayList();
        imageEntityList.add(new ImageEntity());
        given(dataStoreFactory.createCloudDataStore()).willReturn(networkDataStore);
        given(networkDataStore.imageEntityList(anyInt())).willReturn(Observable.just(imageEntityList));

        // Act
        networkRepository.imageList(MOBILE_ID);

        // Assert
        verify(networkDataStore).imageEntityList(MOBILE_ID);
    }
}