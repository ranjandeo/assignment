package com.kumar.ranjan.mobilephone.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class ImageTest {
    private Image image;

    @Before
    public void setUp() {
        image = new Image();
    }

    @Test
    public void testDefaultConstructor() {
        assertThat(image.getId(), is(0));
        assertThat(image.getMobileId(), is(0));
        assertThat(image.getUrl(), is(notNullValue()));
        assertThat(image.getUrl(), is(""));
    }

    @Test
    public void testParameterisedConstructor() {
        image = new Image(1, 1, "url");

        assertThat(image.getId(), is(1));
        assertThat(image.getMobileId(), is(1));
        assertThat(image.getUrl(), is(notNullValue()));
        assertThat(image.getUrl(), is("url"));
    }

    @Test
    public void testGetterSetter() {
        image.setId(1);
        image.setMobileId(1);
        image.setUrl("url");

        assertThat(image.getId(), is(1));
        assertThat(image.getMobileId(), is(1));
        assertThat(image.getUrl(), is(notNullValue()));
        assertThat(image.getUrl(), is("url"));
    }
}