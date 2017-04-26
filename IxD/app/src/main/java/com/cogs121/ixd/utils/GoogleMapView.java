package com.cogs121.ixd.utils;

import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Chad on 4/26/17.
 *
 *
 * Here is where we will be able to add custom pins and do anything related to the map view
 */

public class GoogleMapView {

    private final GoogleMap mapView;

    public GoogleMapView(Context context, GoogleMap googleMap) {
        this.mapView = googleMap;
    }

    public void setOnMapLongClickListener(GoogleMap.OnMapLongClickListener onMapLongClickListener) {
        mapView.setOnMapLongClickListener(onMapLongClickListener);
    }

    public void setOnMapMarkerClickListener(GoogleMap.OnMarkerClickListener onMarkerClickListener) {
        mapView.setOnMarkerClickListener(onMarkerClickListener);
    }

    public void setLocationServices(Boolean enabled) {
        try {
            mapView.setMyLocationEnabled(enabled);
        } catch (SecurityException e) {

        }
    }

    public UiSettings getUISettings() {
        return mapView.getUiSettings();
    }

    public void animateToLocation(LatLng position, float zoom, boolean instant) {
        if (instant) {
            mapView.moveCamera(CameraUpdateFactory.newLatLngZoom(position, zoom));
        } else {
            mapView.animateCamera(CameraUpdateFactory.newLatLngZoom(position, zoom));
        }
    }
}
