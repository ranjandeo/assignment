package com.kumar.ranjan.mobilephone.screen.fragment;

import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.di.components.PhoneDetailsComponent;
import com.kumar.ranjan.mobilephone.model.ImageDataModel;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.router.ActivityRouter;
import com.kumar.ranjan.mobilephone.screen.adapter.ImageListAdapter;
import com.kumar.ranjan.mobilephone.screen.presenter.PhoneDetailsPresenter;

import org.parceler.Parcels;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneDetailsFragment extends BaseFragment implements PhoneDetailsScreen {

    private static int IMAGE_VIEW_HEIGHT_PERCENTGE = 35;

    @Inject
    PhoneDetailsPresenter phoneDetailsPresenter;
    @Inject
    ImageListAdapter imageListAdapter;

    @BindView(R.id.image_list)
    RecyclerView imageListRecyclerView;

    @BindView(R.id.progress_view)
    View progressView;

    @BindView(R.id.error_message_view)
    View errorMessageView;

    @BindView(R.id.text_view_error_message)
    TextView textViewErrorMessage;

    @BindView(R.id.container_image_list)
    RelativeLayout imageListContainer;

    @BindView(R.id.text_view_price)
    TextView textViewPrice;

    @BindView(R.id.text_view_rating)
    TextView textViewRating;

    @BindView(R.id.phone_name)
    TextView textViewPhoneName;

    @BindView(R.id.brand_name)
    TextView textViewBrandName;

    @BindView(R.id.description)
    TextView textViewDescription;

    public PhoneDetailsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(PhoneDetailsComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_phone_details, container, false);
        ButterKnife.bind(this, fragmentView);
        setupRecyclerView();
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        phoneDetailsPresenter.setView(this);

        if (savedInstanceState == null) {
            Bundle bundle = getArguments();
            if (bundle != null && bundle.containsKey(ActivityRouter.INTENT_PHONE_DATA)) {
                PhoneDataModel phoneDataModel = Parcels.unwrap(bundle.getParcelable(ActivityRouter.INTENT_PHONE_DATA));
                loadPhoneDetails(phoneDataModel);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        imageListRecyclerView.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        phoneDetailsPresenter.destroy();
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
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        imageListRecyclerView.setLayoutManager(layoutManager);
        setImageContainerHeight();
        imageListRecyclerView.setAdapter(imageListAdapter);
    }

    private void loadPhoneDetails(PhoneDataModel phoneDataModel) {
        phoneDetailsPresenter.initialize(phoneDataModel);
    }

    @Override
    public void displayImageList(List<ImageDataModel> imageDataModelList) {
        if (imageDataModelList != null) {
            imageListAdapter.setImageDataModelList(imageDataModelList);
        }
    }

    @Override
    public void showPhoneDetailsData(PhoneDataModel phoneDataModel) {
        if (phoneDataModel != null) {
            textViewPhoneName.setText(phoneDataModel.getName());
            textViewBrandName.setText(phoneDataModel.getBrand());
            textViewDescription.setText(phoneDataModel.getDescription());
            textViewPrice.setText(getFormattedPrice(phoneDataModel.getPrice()));
            textViewRating.setText(getFormattedRating(phoneDataModel.getRating()));
        }
    }

    private String getFormattedPrice(double price) {
        String strPrice = String.format(Locale.ENGLISH, "%.2f", price);
        return getResources().getString(R.string.price_text, strPrice);
    }

    private String getFormattedRating(double rating) {
        String strrating = String.format(Locale.ENGLISH, "%.1f", rating);
        return getResources().getString(R.string.rating_text, strrating);
    }

    private void setImageContainerHeight() {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageListContainer.getLayoutParams();
        params.height = getImageViewHeight();
        imageListContainer.setLayoutParams(params);
    }

    private int getImageViewHeight() {
        DisplayMetrics displayMetrics = getActivity().getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        return (height * IMAGE_VIEW_HEIGHT_PERCENTGE) / 100;
    }
}
