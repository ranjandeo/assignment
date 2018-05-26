package com.kumar.ranjan.mobilephone.data.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PhoneEntityTest {

    PhoneEntity phoneEntity;

    @Before
    public void setUp() {
        phoneEntity = new PhoneEntity();
    }

    @Test
    public void testGetId() {
        assertThat(phoneEntity.getId(), is(0));
    }

    @Test
    public void testGetName() {
        assertThat(phoneEntity.getName(), is(notNullValue()));
        assertThat(phoneEntity.getName(), is(""));
    }

    @Test
    public void testGetBrand() {
        assertThat(phoneEntity.getBrand(), is(notNullValue()));
        assertThat(phoneEntity.getBrand(), is(""));
    }

    @Test
    public void testGetDescription() {
        assertThat(phoneEntity.getDescription(), is(notNullValue()));
        assertThat(phoneEntity.getDescription(), is(""));
    }

    @Test
    public void testGetThumbImageURL() {
        assertThat(phoneEntity.getThumbImageURL(), is(notNullValue()));
        assertThat(phoneEntity.getThumbImageURL(), is(""));
    }

    @Test
    public void testGetPrice() {
        assertThat(phoneEntity.getPrice(), is(0.0));
    }

    @Test
    public void testGetRating() {
        assertThat(phoneEntity.getRating(), is(0.0));
    }
}