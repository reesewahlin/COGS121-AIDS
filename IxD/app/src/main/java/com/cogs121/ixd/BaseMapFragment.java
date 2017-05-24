package com.cogs121.ixd;


import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.utils.GoogleMapView;
import com.cogs121.ixd.utils.LocusPoint;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Chad on 4/26/17.
 */

public class BaseMapFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnInfoWindowLongClickListener, PlaceSelectionListener {

    private MapView mapHolder;
    private GoogleMapView googleMapView;
    private GoogleMap gMap;

    private GoogleApiClient googleApiClient;

    private LatLng currentLocation;

    private Address address;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMapView = new GoogleMapView(getContext(), googleMap);
        gMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMapView.setLocationServices(true);
        }

        googleMapView.getUISettings().setMyLocationButtonEnabled(true);
        googleMapView.getUISettings().setMapToolbarEnabled(true);

        //TODO: make this proper
        BitmapDrawable bitmapdraw=(BitmapDrawable)getResources().getDrawable(R.drawable.map_pin);
        Bitmap b=bitmapdraw.getBitmap();
        final Bitmap smallMarker = Bitmap.createScaledBitmap(b, 125, 175, false);

        googleMapView.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                gMap.addMarker(new MarkerOptions()
                        .title("Create new promo?")
                        .snippet("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .position(latLng));
            }
        });

        gMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                getStoreFactory().getLocationStore().setPromoLocation(marker.getPosition());
                LocusPoint lp = getStoreFactory().getLocusPointStore().getLocusPointByLocation(marker.getPosition().toString());
                if (lp != null) {
                    marker.setTitle(lp.getLpTitle());
                }

                return false;
            }
        });

        gMap.setOnInfoWindowClickListener(this);


        animateToCurrLocation(true);
        mapHolder.onResume();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mapHolder.onCreate(savedInstanceState);
        mapHolder.getMapAsync(this);

        PlaceAutocompleteFragment placeAutocompleteFragment = (PlaceAutocompleteFragment)
                getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        placeAutocompleteFragment.setOnPlaceSelectedListener(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setMapHolder(MapView googleMapHolder) {
        this.mapHolder = googleMapHolder;
    }

    private void animateToCurrLocation(boolean instant) {
        if (getStoreFactory() == null) {
            return;
        }
        LatLng currentPosition = getStoreFactory().getLocationStore().getCurrentLocation();
        if (currentPosition == null) {
            return;
        }

        googleMapView.animateToLocation(currentPosition, 17f, instant);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        LocusPoint lp  = getStoreFactory().getLocusPointStore().getLocusPointByLocation(marker.getPosition().toString());
        if(lp == null) {
            marker.hideInfoWindow();
            getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_PROMO_FORM);
        } else {
            marker.hideInfoWindow();
            getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_PROMO);
        }

    }

    @Override
    public void onInfoWindowLongClick(Marker marker) {
        marker.remove();
    }

    @Override
    public void onPlaceSelected(Place place) {
        LatLng search = place.getLatLng();
        googleMapView.animateToLocation(search, 17f, true);
    }

    @Override
    public void onError(Status status) {

    }
}
