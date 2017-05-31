package com.cogs121.ixd.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.cogs121.ixd.BaseActivity;
import com.cogs121.ixd.Controllers.navigation.NavigationControllerObserver;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;
import com.cogs121.ixd.activities.main.MainActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

/**
 * Created by Chad on 5/30/17.
 */

public class SearchActivity extends BaseActivity implements NavigationControllerObserver {

    public SearchActivity() {
        page = Page.SEARCH;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                getStoreFactory().getSearchStore().setSearchPlace(place);
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
            }

            @Override
            public void onError(Status status) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getControllerFactory().getNavigationController().addObserver(this);
    }

    private void transitionToMainActivity() {
        getControllerFactory().getNavigationController().removeObserver(this);
        getControllerFactory().getNavigationController().overrideSearchPage();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

    }

    @Override
    public void onPageTransition(Page fromPage, Page toPage) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (toPage) {
            case MAIN_MAP:
                transitionToMainActivity();
                break;
            default:
                break;
        }
    }
}
