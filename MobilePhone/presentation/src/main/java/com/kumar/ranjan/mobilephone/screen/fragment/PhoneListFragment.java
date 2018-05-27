package com.kumar.ranjan.mobilephone.screen.fragment;

import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.di.components.PhoneListComponent;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.adapter.PhoneListAdapter;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;
import com.kumar.ranjan.mobilephone.screen.presenter.PhoneListPresenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneListFragment extends BaseFragment implements PhoneListScreen {

    @Inject
    PhoneListPresenter phoneListPresenter;

    @Inject
    PhoneListAdapter phoneListAdapter;

    @BindView(R.id.phone_list)
    RecyclerView phoneListRecyclerView;

    @BindView(R.id.progress_view)
    View progressView;

    @BindView(R.id.error_message_view)
    View errorMessageView;

    @BindView(R.id.text_view_error_message)
    TextView textViewErrorMessage;

    private PhoneListItemClickListener phoneListItemClickListener;

    public PhoneListFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof PhoneListItemClickListener) {
            phoneListItemClickListener = (PhoneListItemClickListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(PhoneListComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_phone_list, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phoneListPresenter.setView(this);
        if (savedInstanceState == null) {
            loadPhoneList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        phoneListRecyclerView.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        phoneListPresenter.destroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        phoneListItemClickListener = null;
    }

    @Override
    public void showLoading() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        errorMessageView.setVisibility(View.VISIBLE);
        textViewErrorMessage.setText(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }

    private void setupRecyclerView() {
        phoneListAdapter.setOnItemClickListener(onItemClickListener);
        phoneListRecyclerView.setLayoutManager(new LinearLayoutManager(context()));
        phoneListRecyclerView.setAdapter(phoneListAdapter);
    }

    private void loadPhoneList() {
        phoneListPresenter.initialize();
    }

    private PhoneListAdapter.OnItemClickListener onItemClickListener =
            new PhoneListAdapter.OnItemClickListener() {
                @Override public void onPhoneItemClicked(PhoneDataModel phoneDataModel) {
                    if (phoneListPresenter != null && phoneDataModel != null) {
                        phoneListPresenter.onPhoneListItemClicked(phoneDataModel);
                    }
                }
            };

    @Override
    public void displayPhoneList(List<PhoneDataModel> phoneDataModelList) {
        if (phoneDataModelList != null) {
            phoneListAdapter.setPhoneDataModelList(phoneDataModelList);
        }
    }

    @Override
    public void showPhoneDetails(PhoneDataModel phoneDataModel) {
        if (phoneListItemClickListener != null) {
            phoneListItemClickListener.onPhoneItemClicked(phoneDataModel);
        }
    }

    @Override
    public void applySorting(SortOptionType sortOptionType) {
        phoneListPresenter.applySorting(sortOptionType);
    }
}
