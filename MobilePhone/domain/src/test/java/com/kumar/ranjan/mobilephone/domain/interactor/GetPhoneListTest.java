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
public class GetPhoneListTest {

    private GetPhoneList getPhoneList;

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
        getPhoneList = new GetPhoneList(mockNetworkRepository, mockThreadExecutor, mockPostExecutionThread);
    }

    @Test
    public void testGetImageListUseCaseObservable() {
        getPhoneList.buildUseCaseObservable(null);

        verify(mockNetworkRepository).phoneList();
        verifyNoMoreInteractions(mockNetworkRepository);
        verifyZeroInteractions(mockThreadExecutor);
        verifyZeroInteractions(mockPostExecutionThread);
    }
}