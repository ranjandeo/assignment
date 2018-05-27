package com.kumar.ranjan.mobilephone.data.entity.mapper;

import com.kumar.ranjan.mobilephone.data.entity.PhoneEntity;
import com.kumar.ranjan.mobilephone.domain.Phone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.assertj.core.util.Lists;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhoneEntityMapperTest {

    PhoneEntityMapper mapper;

    @Before
    public void setUp() {
        mapper = new PhoneEntityMapper();
    }

    @Test
    public void testTransformPhoneEntityObject() {
        // Arrange
        PhoneEntity phoneEntity = getMockPhoneEntity(1, "Name", "Brand", "Description", "url", 100.0, 4.0);

        // Act
        Phone phone = mapper.transform(phoneEntity);

        // Assert
        assertThat(phone, is(notNullValue()));
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
        assertThat(phone.isFavorite(), is(false));
    }

    @Test
    public void testTransformPhoneEntityList() {
        // Arrange
        List<PhoneEntity> phoneEntityList = Lists.newArrayList();
        phoneEntityList.add(getMockPhoneEntity(1, "Name", "Brand", "Description", "url", 100.0, 4.0));
        phoneEntityList.add(getMockPhoneEntity(2, "Name", "Brand", "Description", "url", 200.0, 5.0));

        // Act
        List<Phone> phoneList = mapper.transform(phoneEntityList);

        // Assert

        assertThat(phoneList, is(notNullValue()));
        assertThat(phoneList.isEmpty(), is(false));
        assertThat(phoneList.size(), is(2));

        assertThat(phoneList.get(0), is(instanceOf(Phone.class)));
        assertThat(phoneList.get(0), is(notNullValue()));
        assertThat(phoneList.get(0).getId(), is(1));
        assertThat(phoneList.get(0).getName(), is(notNullValue()));
        assertThat(phoneList.get(0).getName(), is("Name"));
        assertThat(phoneList.get(0).getBrand(), is(notNullValue()));
        assertThat(phoneList.get(0).getBrand(), is("Brand"));
        assertThat(phoneList.get(0).getDescription(), is(notNullValue()));
        assertThat(phoneList.get(0).getDescription(), is("Description"));
        assertThat(phoneList.get(0).getThumbImageURL(), is(notNullValue()));
        assertThat(phoneList.get(0).getThumbImageURL(), is("url"));
        assertThat(phoneList.get(0).getPrice(), is(100.0));
        assertThat(phoneList.get(0).getRating(), is(4.0));
        assertThat(phoneList.get(0).isFavorite(), is(false));
    }

    private PhoneEntity getMockPhoneEntity(int id, String name, String brand, String description, String url, double price, double rating) {
        PhoneEntity phoneEntity = mock(PhoneEntity.class);
        when(phoneEntity.getId()).thenReturn(id);
        when(phoneEntity.getName()).thenReturn(name);
        when(phoneEntity.getBrand()).thenReturn(brand);
        when(phoneEntity.getDescription()).thenReturn(description);
        when(phoneEntity.getThumbImageURL()).thenReturn(url);
        when(phoneEntity.getPrice()).thenReturn(price);
        when(phoneEntity.getRating()).thenReturn(rating);
        return phoneEntity;
    }
}