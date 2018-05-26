package com.kumar.ranjan.mobilephone.mapper;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.domain.Phone;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PhoneDataModelMapperTest {
    private PhoneDataModelMapper phoneDataModelMapper;

    @Before
    public void setUp() {
        phoneDataModelMapper = new PhoneDataModelMapper();
    }

    @Test
    public void transformObject() {
        // Arrange
        Phone phone = getMockPhone(1, "name", "brand", "description", "url", 100.0, 4.0);

        // Act
        PhoneDataModel phoneDataModel = phoneDataModelMapper.transform(phone);

        // Assert
        assertThat(phoneDataModel, is(notNullValue()));
        assertThat(phoneDataModel.getId(), is(1));
        assertThat(phoneDataModel.getName(), is(notNullValue()));
        assertThat(phoneDataModel.getName(), is("name"));
        assertThat(phoneDataModel.getBrand(), is(notNullValue()));
        assertThat(phoneDataModel.getBrand(), is("brand"));
        assertThat(phoneDataModel.getDescription(), is(notNullValue()));
        assertThat(phoneDataModel.getDescription(), is("description"));
        assertThat(phoneDataModel.getThumbImageURL(), is(notNullValue()));
        assertThat(phoneDataModel.getThumbImageURL(), is("url"));
        assertThat(phoneDataModel.getPrice(), is(100.0));
        assertThat(phoneDataModel.getRating(), is(4.0));
    }

    @Test
    public void transformList() {
        // Arrange
        List<Phone> phoneList = Lists.newArrayList();
        phoneList.add(getMockPhone(1, "name", "brand", "description", "url", 100.0, 4.0));
        phoneList.add(getMockPhone(2, "name2", "brand2", "description2", "url2", 200.0, 4.5));

        // Act
        List<PhoneDataModel> phoneDataModelList = phoneDataModelMapper.transform(phoneList);

        // Assert
        assertThat(phoneDataModelList, is(notNullValue()));
        assertThat(phoneDataModelList.isEmpty(), is(false));
        assertThat(phoneDataModelList.size(), is(2));
        assertThat(phoneDataModelList.get(0), is(CoreMatchers.<PhoneDataModel>instanceOf(PhoneDataModel.class)));
    }

    private Phone getMockPhone(int id, String name, String brand, String description, String url, double price, double rating) {
        Phone phone = new Phone();

        phone.setId(id);
        phone.setName(name);
        phone.setBrand(brand);
        phone.setDescription(description);
        phone.setThumbImageURL(url);
        phone.setPrice(price);
        phone.setRating(rating);

        return phone;
    }
}