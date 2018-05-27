package com.kumar.ranjan.mobilephone.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PhoneDataModelTest {
    private PhoneDataModel phoneDataModel;

    @Before
    public void setUp() {
        phoneDataModel = new PhoneDataModel();
    }

    @Test
    public void testDefaultConstructor() {
        assertThat(phoneDataModel.getId(), is(0));
        assertThat(phoneDataModel.getName(), is(notNullValue()));
        assertThat(phoneDataModel.getName(), is(""));
        assertThat(phoneDataModel.getBrand(), is(notNullValue()));
        assertThat(phoneDataModel.getBrand(), is(""));
        assertThat(phoneDataModel.getDescription(), is(notNullValue()));
        assertThat(phoneDataModel.getDescription(), is(""));
        assertThat(phoneDataModel.getThumbImageURL(), is(notNullValue()));
        assertThat(phoneDataModel.getThumbImageURL(), is(""));
        assertThat(phoneDataModel.getPrice(), is(0.0));
        assertThat(phoneDataModel.getRating(), is(0.0));
        assertThat(phoneDataModel.isFavorite(), is(false));
    }

    @Test
    public void testParameterisedConstructor() {
        phoneDataModel = new PhoneDataModel(1, "Name", "Brand", "Description", "url", 100.0, 4.0, false);

        assertThat(phoneDataModel.getId(), is(1));
        assertThat(phoneDataModel.getName(), is(notNullValue()));
        assertThat(phoneDataModel.getName(), is("Name"));
        assertThat(phoneDataModel.getBrand(), is(notNullValue()));
        assertThat(phoneDataModel.getBrand(), is("Brand"));
        assertThat(phoneDataModel.getDescription(), is(notNullValue()));
        assertThat(phoneDataModel.getDescription(), is("Description"));
        assertThat(phoneDataModel.getThumbImageURL(), is(notNullValue()));
        assertThat(phoneDataModel.getThumbImageURL(), is("url"));
        assertThat(phoneDataModel.getPrice(), is(100.0));
        assertThat(phoneDataModel.getRating(), is(4.0));
        assertThat(phoneDataModel.isFavorite(), is(false));
    }

    @Test
    public void testGetterSetter() {
        phoneDataModel.setId(1);
        phoneDataModel.setName("Name");
        phoneDataModel.setBrand("Brand");
        phoneDataModel.setDescription("Description");
        phoneDataModel.setThumbImageURL("url");
        phoneDataModel.setPrice(100.0);
        phoneDataModel.setRating(4.0);
        phoneDataModel.setFavorite(true);

        assertThat(phoneDataModel.getId(), is(1));
        assertThat(phoneDataModel.getName(), is(notNullValue()));
        assertThat(phoneDataModel.getName(), is("Name"));
        assertThat(phoneDataModel.getBrand(), is(notNullValue()));
        assertThat(phoneDataModel.getBrand(), is("Brand"));
        assertThat(phoneDataModel.getDescription(), is(notNullValue()));
        assertThat(phoneDataModel.getDescription(), is("Description"));
        assertThat(phoneDataModel.getThumbImageURL(), is(notNullValue()));
        assertThat(phoneDataModel.getThumbImageURL(), is("url"));
        assertThat(phoneDataModel.getPrice(), is(100.0));
        assertThat(phoneDataModel.getRating(), is(4.0));
        assertThat(phoneDataModel.isFavorite(), is(true));
    }

}