package com.kumar.ranjan.mobilephone.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ImageDataModelTest {

    private ImageDataModel imageDataModel;

    @Before
    public void setUp() {
        imageDataModel = new ImageDataModel();
    }

    @Test
    public void testDefaultConstructor() {
        assertThat(imageDataModel.getId(), is(0));
        assertThat(imageDataModel.getMobileId(), is(0));
        assertThat(imageDataModel.getUrl(), is(notNullValue()));
        assertThat(imageDataModel.getUrl(), is(""));
    }

    @Test
    public void testParameterisedConstructor() {
        imageDataModel = new ImageDataModel(1, 1, "url");

        assertThat(imageDataModel.getId(), is(1));
        assertThat(imageDataModel.getMobileId(), is(1));
        assertThat(imageDataModel.getUrl(), is(notNullValue()));
        assertThat(imageDataModel.getUrl(), is("url"));
    }

    @Test
    public void testGetterSetter() {
        imageDataModel.setId(1);
        imageDataModel.setMobileId(1);
        imageDataModel.setUrl("url");

        assertThat(imageDataModel.getId(), is(1));
        assertThat(imageDataModel.getMobileId(), is(1));
        assertThat(imageDataModel.getUrl(), is(notNullValue()));
        assertThat(imageDataModel.getUrl(), is("url"));
    }
}