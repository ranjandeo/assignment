package com.kumar.ranjan.mobilephone.screen.fragment;

import com.kumar.ranjan.mobilephone.di.HasComponent;

import android.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity())
                .getComponent());
    }
}
