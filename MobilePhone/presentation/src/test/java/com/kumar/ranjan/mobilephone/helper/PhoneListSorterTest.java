package com.kumar.ranjan.mobilephone.helper;

import com.google.common.collect.Lists;

import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class PhoneListSorterTest {

    private PhoneListSorter phoneListSorter;

    @Before
    public void setUp() {
        phoneListSorter = new PhoneListSorter();
    }

    @Test(expected = NullPointerException.class)
    public void sort_When_Method_Parameters_Null() {
        phoneListSorter.sort(null, null);
    }

    @Test
    public void testSort_Price_Low_To_High() {
        List<PhoneDataModel> sortedList = phoneListSorter.sort(createList(), SortOptionType.PRICE_LOW_TO_HIGH);

        assertThat(sortedList, is(notNullValue()));
        assertThat(sortedList.isEmpty(), is(false));
        assertThat(sortedList.size(), is(4));

        assertThat(sortedList.get(0).getPrice(), is(99.76));
        assertThat(sortedList.get(1).getPrice(), is(109.66));
        assertThat(sortedList.get(2).getPrice(), is(209.69));
        assertThat(sortedList.get(3).getPrice(), is(309.87));
    }

    @Test
    public void testSort_Price_High_To_Low() {
        List<PhoneDataModel> sortedList = phoneListSorter.sort(createList(), SortOptionType.PRICE_HIGH_TO_LOW);

        assertThat(sortedList, is(notNullValue()));
        assertThat(sortedList.isEmpty(), is(false));
        assertThat(sortedList.size(), is(4));

        assertThat(sortedList.get(0).getPrice(), is(309.87));
        assertThat(sortedList.get(1).getPrice(), is(209.69));
        assertThat(sortedList.get(2).getPrice(), is(109.66));
        assertThat(sortedList.get(3).getPrice(), is(99.76));
    }

    @Test
    public void testSort_by_Rating_5_To_1() {
        List<PhoneDataModel> sortedList = phoneListSorter.sort(createList(), SortOptionType.RATING_5_To_1);

        assertThat(sortedList, is(notNullValue()));
        assertThat(sortedList.isEmpty(), is(false));
        assertThat(sortedList.size(), is(4));

        assertThat(sortedList.get(0).getRating(), is(4.9));
        assertThat(sortedList.get(1).getRating(), is(4.1));
        assertThat(sortedList.get(2).getRating(), is(3.6));
        assertThat(sortedList.get(3).getRating(), is(2.5));
    }

    private List<PhoneDataModel> createList() {
        List<PhoneDataModel> list = Lists.newArrayList();
        list.add(new PhoneDataModel(1, "name", "brand", "description", "url", 109.66, 4.1, false));
        list.add(new PhoneDataModel(2, "name", "brand", "description", "url", 99.76, 4.9, false));
        list.add(new PhoneDataModel(3, "name", "brand", "description", "url", 309.87, 2.5, false));
        list.add(new PhoneDataModel(4, "name", "brand", "description", "url", 209.69, 3.6, false));
        return list;
    }
}