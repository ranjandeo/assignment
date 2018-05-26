package com.kumar.ranjan.mobilephone.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PhoneTest {

    private Phone phone;

    @Before
    public void setUp() {
        phone = new Phone();
    }

    @Test
    public void testDefaultConstructor() {
        assertThat(phone.getId(), is(0));
        assertThat(phone.getName(), is(notNullValue()));
        assertThat(phone.getName(), is(""));
        assertThat(phone.getBrand(), is(notNullValue()));
        assertThat(phone.getBrand(), is(""));
        assertThat(phone.getDescription(), is(notNullValue()));
        assertThat(phone.getDescription(), is(""));
        assertThat(phone.getThumbImageURL(), is(notNullValue()));
        assertThat(phone.getThumbImageURL(), is(""));
        assertThat(phone.getPrice(), is(0.0));
        assertThat(phone.getRating(), is(0.0));
    }

    @Test
    public void testParameterisedConstructor() {
        phone = new Phone(1, "Name", "Brand", "Description", "url", 100.0, 4.0);

        assertThat(phone.getId(), is(1));
        assertThat(phone.getName(), is(notNullValue()));
        assertThat(phone.getName(), is("Name"));
        assertThat(phone.getBrand(), is(notNullValue()));
        assertThat(phone.getBrand(), is("Brand"));
        assertThat(phone.getDescription(), is(notNullValue()));
        assertThat(phone.getDescription(), is("Description"));
        assertThat(phone.getThumbImageURL(), is(notNullValue()));
        assertThat(phone.getThumbImageURL(), is("url"));
        assertThat(phone.getPrice(), is(100.0));
        assertThat(phone.getRating(), is(4.0));
    }

    @Test
    public void testGetterSetter() {
        phone.setId(1);
        phone.setName("Name");
        phone.setBrand("Brand");
        phone.setDescription("Description");
        phone.setThumbImageURL("url");
        phone.setPrice(100.0);
        phone.setRating(4.0);

        assertThat(phone.getId(), is(1));
        assertThat(phone.getName(), is(notNullValue()));
        assertThat(phone.getName(), is("Name"));
        assertThat(phone.getBrand(), is(notNullValue()));
        assertThat(phone.getBrand(), is("Brand"));
        assertThat(phone.getDescription(), is(notNullValue()));
        assertThat(phone.getDescription(), is("Description"));
        assertThat(phone.getThumbImageURL(), is(notNullValue()));
        assertThat(phone.getThumbImageURL(), is("url"));
        assertThat(phone.getPrice(), is(100.0));
        assertThat(phone.getRating(), is(4.0));
    }
}
