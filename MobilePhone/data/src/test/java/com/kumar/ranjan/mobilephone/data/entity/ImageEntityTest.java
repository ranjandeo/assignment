package com.kumar.ranjan.mobilephone.data.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ImageEntityTest {

    ImageEntity imageEntity;

    @Before
    public void setUp() {
        imageEntity = new ImageEntity();
    }

    @Test
    public void testGetId() {
        assertThat(imageEntity.getId(), is(0));
    }

    @Test
    public void testGetMobileId() {
        assertThat(imageEntity.getMobileId(), is(0));
    }

    @Test
    public void testGetUrl() {
        assertThat(imageEntity.getUrl(), is(notNullValue()));
        assertThat(imageEntity.getUrl(), is(""));
    }
}