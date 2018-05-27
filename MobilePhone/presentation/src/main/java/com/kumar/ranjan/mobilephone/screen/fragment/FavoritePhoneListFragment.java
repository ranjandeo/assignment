package com.kumar.ranjan.mobilephone.screen.fragment;

import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.di.components.PhoneListComponent;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.adapter.FavoriteListAdapter;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;
import com.kumar.ranjan.mobilephone.screen.helper.SwipeController;
import com.kumar.ranjan.mobilephone.screen.helper.SwipeControllerActions;
import com.kumar.ranjan.mobilephone.screen.presenter.FavoritePhoneListPresenter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritePhoneListFragment extends BaseFragment implements FavoriteListScreen {

    @Inject
    FavoritePhoneListPresenter favoritePhoneListPresenter;

    @Inject
    FavoriteListAdapter favoriteListAdapter;

    @BindView(R.id.phone_list)
    RecyclerView favoritesListRecyclerView;

    @BindView(R.id.progress_view)
    View progressView;

    @BindView(R.id.error_message_view)
    View errorMessageView;

    @BindView(R.id.text_view_error_message)
    TextView textViewErrorMessage;

    private SwipeController swipeController;

    private PhoneListItemClickListener phoneListItemClickListener;

    public FavoritePhoneListFragment() {
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
        favoritePhoneListPresenter.setView(this);
        if (savedInstanceState == null) {
            loadFavoriteList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        favoritesListRecyclerView.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoritePhoneListPresenter.destroy();
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
        favoriteListAdapter.setOnItemClickListener(onItemClickListener);
        favoritesListRecyclerView.setLayoutManager(new LinearLayoutManager(context(), LinearLayoutManager.VERTICAL, false));

        favoritesListRecyclerView.setAdapter(favoriteListAdapter);

        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                handleOnRemoveFromFavorite(position);
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(favoritesListRecyclerView);

        favoritesListRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }

    public void loadFavoriteList() {
        favoritePhoneListPresenter.initialize();
    }

    private FavoriteListAdapter.OnItemClickListener onItemClickListener =
            new FavoriteListAdapter.OnItemClickListener() {
                @Override
                public void onPhoneItemClicked(PhoneDataModel phoneDataModel) {
                    if (favoritePhoneListPresenter != null && phoneDataModel != null) {
                        favoritePhoneListPresenter.onPhoneListItemClicked(phoneDataModel);
                    }
                }
            };

    @Override
    public void displayFavoriteList(List<PhoneDataModel> phoneDataModelList) {
        if (phoneDataModelList != null) {
            favoriteListAdapter.setPhoneDataModelList(phoneDataModelList);
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
        favoritePhoneListPresenter.applySorting(sortOptionType);
    }

    @Override
    public void showNoFavoritesMessage() {
        showError(getString(R.string.no_favorite_mobile));
    }

    @Override
    public void refreshFavoriteList() {
        loadFavoriteList();
    }

    @Override
    public void hideErrorMessage() {
        errorMessageView.setVisibility(View.GONE);
    }

    private void handleOnRemoveFromFavorite(int itemPosition) {
        PhoneDataModel phoneDataModel = favoriteListAdapter.getItemAtPosition(itemPosition);
        favoritePhoneListPresenter.removeItemFromList(phoneDataModel);
        favoriteListAdapter.removeItemAtPosition(itemPosition);
        favoriteListAdapter.notifyDataSetChanged();

        if (phoneListItemClickListener != null) {
            phoneListItemClickListener.onRemovedFromFavorite(phoneDataModel);
        }
    }
}
