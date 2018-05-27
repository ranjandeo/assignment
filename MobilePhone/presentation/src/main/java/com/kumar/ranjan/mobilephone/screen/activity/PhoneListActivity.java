package com.kumar.ranjan.mobilephone.screen.activity;

import com.kumar.ranjan.mobilephone.R;
import com.kumar.ranjan.mobilephone.di.HasComponent;
import com.kumar.ranjan.mobilephone.di.components.DaggerPhoneListComponent;
import com.kumar.ranjan.mobilephone.di.components.PhoneListComponent;
import com.kumar.ranjan.mobilephone.model.PhoneDataModel;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionDialogFragment;
import com.kumar.ranjan.mobilephone.screen.dialog.SortOptionType;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListFragment;
import com.kumar.ranjan.mobilephone.screen.fragment.PhoneListItemClickListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class PhoneListActivity extends BaseAppCompatActivity implements HasComponent<PhoneListComponent>,
        PhoneListItemClickListener, SortOptionDialogFragment.SortOptionSelectionListener {

    private static String SORT_DIALOG = "sort_dialog";

    private PhoneListComponent phoneListComponent;
    private SortOptionDialogFragment dialogFragment;
    private PhoneListFragment phoneListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_list);
        dialogFragment = new SortOptionDialogFragment();

        initializeInjector();
        if (savedInstanceState == null) {
            phoneListFragment = new PhoneListFragment();
            addFragment(R.id.fragmentContainer, phoneListFragment);
        }
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
        if (phoneListFragment != null) {
            phoneListFragment.applySorting(sortOptionType);
        }
    }
}

