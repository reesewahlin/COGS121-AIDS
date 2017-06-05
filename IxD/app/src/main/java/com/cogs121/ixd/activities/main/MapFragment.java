package com.cogs121.ixd.activities.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cogs121.ixd.BaseMapFragment;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;
import com.cogs121.ixd.utils.ViewUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

/**
 * Created by Chad on 4/26/17.
 */

public class MapFragment extends BaseMapFragment implements GoogleApiClient.OnConnectionFailedListener {

    public static final String TAG = MapFragment.class.getName();

    private MapView mapView;
    private GoogleMap googleMap;

    private TextView searchBar;
    private ListView searchResults;
    private FloatingActionButton openMenuFAB;
    private FloatingActionButton openLocusPointPageFAB;
    private FloatingActionButton homeButtonFAB;
    private Button openPromoForm;

    private String query;

    private GoogleApiClient googleApiClient;

    private boolean isMenuOpen = false;

    public MapFragment() {
        page = Page.MAIN_MAP;
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        searchBar = ViewUtils.getView(rootView, R.id.et_search_bar);
        openMenuFAB = ViewUtils.getView(rootView, R.id.fab_open_menu);
        openLocusPointPageFAB = ViewUtils.getView(rootView, R.id.fab_open_locus_points);
        homeButtonFAB = ViewUtils.getView(rootView, R.id.fab_home);

        Place place = getStoreFactory().getSearchStore().getSearchPlace();
        if (place != null) {
            searchBar.setText(place.getAddress().toString());
        }


        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.SEARCH);
            }
        });

        openLocusPointPageFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_FAVORITES);
            }
        });

        homeButtonFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_HOME);
            }
        });

        openMenuFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation fadeIn = AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
                Animation fadeOut = AnimationUtils.loadAnimation(getContext(), R.anim.fade_out);
                if (!isMenuOpen) {
                    fadeIn.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            openMenuFAB.setImageResource(R.mipmap.ic_remove_white_24dp);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            homeButtonFAB.setVisibility(View.VISIBLE);
                            openLocusPointPageFAB.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    isMenuOpen = true;
                    homeButtonFAB.startAnimation(fadeIn);
                    openLocusPointPageFAB.startAnimation(fadeIn);
                } else {
                    fadeOut.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            openMenuFAB.setImageResource(R.mipmap.ic_add_white_24dp);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            homeButtonFAB.setVisibility(View.GONE);
                            openLocusPointPageFAB.setVisibility(View.GONE);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    isMenuOpen = false;
                    homeButtonFAB.startAnimation(fadeOut);
                    openLocusPointPageFAB.startAnimation(fadeOut);
                }




            }
        });

        if (checkIfEnterprise()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Please verify your account by following the instructions provided in your username email to continue").setTitle("Account Verification Needed");
            AlertDialog dialog = builder.create();
            builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            dialog.show();
        }

        setMapHolder((MapView) ViewUtils.getView(rootView, R.id.fl_main_map_holder));
        super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

    private boolean checkIfEnterprise() {
        if (getStoreFactory().getEnterpriseUserStore().isEnterprise()) {
            return true;
        } else {
            return false;
        }
    }

}
