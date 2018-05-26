package com.kumar.ranjan.mobilephone.domain.interactor;

import com.kumar.ranjan.mobilephone.domain.executor.PostExecutionThread;
import com.kumar.ranjan.mobilephone.domain.executor.ThreadExecutor;
import com.kumar.ranjan.mobilephone.domain.repository.NetworkRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;


@RunWith(MockitoJUnitRunner.class)
public class GetImageListTest {
    private static final int MOBILE_ID = 1;

    private GetImageList getImageList;

    @Mock
    private NetworkRepository mockNetworkRepository;
    @Mock
    private ThreadExecutor mockThreadExecutor;
    @Mock
    private PostExecutionThread mockPostExecutionThread;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        getImageList = new GetImageList(mockNetworkRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testGetImageListUseCaseObservable() {
        getImageList.buildUseCaseObservable(GetImageList.Params.forPhone(MOBILE_ID));

        verify(mockNetworkRepository).imageList(MOBILE_ID);
        verifyNoMoreInteractions(mockNetworkRepository);
        verifyZeroInteractions(mockPostExecutionThread);
        verifyZeroInteractions(mockThreadExecutor);
    }
}