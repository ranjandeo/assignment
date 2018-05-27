package com.kumar.ranjan.mobilephone.screen.adapter;

import com.kumar.ranjan.mobilephone.screen.fragment.FavoritePhoneListFragment;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class FragmentsAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private PhoneListFragment phoneListFragment;
    private FavoritePhoneListFragment favoritePhoneListFragment;

    public FragmentsAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
        phoneListFragment = new PhoneListFragment();
        favoritePhoneListFragment = new FavoritePhoneListFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return phoneListFragment;
            case 1:
                return favoritePhoneListFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}