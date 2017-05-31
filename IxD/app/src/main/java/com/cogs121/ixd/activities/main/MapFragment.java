package com.cogs121.ixd.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView test;

    private Button openPromoForm;

    private String query;

    private GoogleApiClient googleApiClient;

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


        setMapHolder((MapView) ViewUtils.getView(rootView, R.id.fl_main_map_holder));
        super.onCreateView(inflater, container, savedInstanceState);

        return rootView;
    }

}
