package com.kumar.ranjan.mobilephone.helper;

import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PhoneListSorter {

    public List<PhoneDataModel> sort(@NonNull List<PhoneDataModel> sourceList, @NonNull SortOptionType sortOptionType) {
        switch (sortOptionType) {
            case PRICE_LOW_TO_HIGH:
                return sortByPriceLowToHigh(sourceList);
            case PRICE_HIGH_TO_LOW:
                return sortByPriceHighToLow(sourceList);
            case RATING_5_To_1:
                return sortByRatingHighToLow(sourceList);
                default:
                    return sourceList;
        }
    }

    private List<PhoneDataModel> sortByPriceLowToHigh(List<PhoneDataModel> sourceList) {
        Collections.sort(sourceList, new Comparator<PhoneDataModel>() {
            @Override
            public int compare(PhoneDataModel ob1, PhoneDataModel ob2) {
                return Double.compare(ob1.getPrice(), ob2.getPrice());
            }
        });
        return sourceList;
    }

    private List<PhoneDataModel> sortByPriceHighToLow(List<PhoneDataModel> sourceList) {
        Collections.sort(sourceList, new Comparator<PhoneDataModel>() {
            @Override
            public int compare(PhoneDataModel ob1, PhoneDataModel ob2) {
                return Double.compare(ob2.getPrice(), ob1.getPrice());
            }
        });
        return sourceList;
    }

    private List<PhoneDataModel> sortByRatingHighToLow(List<PhoneDataModel> sourceList) {
        Collections.sort(sourceList, new Comparator<PhoneDataModel>() {
            @Override
            public int compare(PhoneDataModel ob1, PhoneDataModel ob2) {
                return Double.compare(ob2.getRating(), ob1.getRating());
            }
        });
        return sourceList;
    }
}
