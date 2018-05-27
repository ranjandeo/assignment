package com.kumar.ranjan.mobilephone.screen.activity;

import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.di.HasComponent;
import com.kumar.ranjan.mobilephone.di.components.DaggerPhoneListComponent;
import com.kumar.ranjan.mobilephone.di.components.PhoneListComponent;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.adapter.FragmentsAdapter;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionDialogFragment;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;
import com.kumar.ranjan.mobilephone.screen.fragment.FavoriteListScreen;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListItemClickListener;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListScreen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneListActivity extends BaseAppCompatActivity implements HasComponent<PhoneListComponent>,
        PhoneListItemClickListener, SortOptionDialogFragment.SortOptionSelectionListener, TabLayout.OnTabSelectedListener {

    private static String SORT_DIALOG = "sort_dialog";

    private PhoneListComponent phoneListComponent;
    private SortOptionDialogFragment dialogFragment;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.fragment_view_pager)
    ViewPager viewPager;

    private FragmentsAdapter fragmentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);

        ButterKnife.bind(this);

        dialogFragment = new SortOptionDialogFragment();

        initializeInjector();

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.mobile_list)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.favorite_list)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(this);

        fragmentsAdapter = new FragmentsAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(fragmentsAdapter);
    }

    private void initializeInjector() {
        phoneListComponent = DaggerPhoneListComponent.builder()
                                                .applicationComponent(getApplicationComponent())
                                                .activityModule(getActivityModule())
                                                .build();
    }

    @Override
    public PhoneListComponent getComponent() {
        return phoneListComponent;
    }

    @Override
    public void onPhoneItemClicked(PhoneDataModel phoneDataModel) {
        activityRouter.goToPhoneDetailsPage(this, phoneDataModel);
    }

    @Override
    public void onMarkedAsFavorite(PhoneDataModel phoneDataModel) {
        if (fragmentsAdapter != null) {
            ((FavoriteListScreen) fragmentsAdapter.getItem(1)).refreshFavoriteList();
        }
    }

    @Override
    public void onRemovedFromFavorite(PhoneDataModel phoneDataModel) {
        if (fragmentsAdapter != null) {
            ((PhoneListScreen) fragmentsAdapter.getItem(0)).onRemedFromFavorite(phoneDataModel);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_sort:
                dialogFragment.show(getSupportFragmentManager(), SORT_DIALOG);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.findItem(R.id.menu_sort).setEnabled(true);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onSortOptionSelected(SortOptionType sortOptionType) {
        if (fragmentsAdapter != null) {
            ((PhoneListScreen) fragmentsAdapter.getItem(0)).applySorting(sortOptionType);
            ((FavoriteListScreen) fragmentsAdapter.getItem(1)).applySorting(sortOptionType);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

