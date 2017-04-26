package com.cogs121.ixd;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cogs121.ixd.utils.GoogleMapView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Chad on 4/26/17.
 */

public class BaseMapFragment extends BaseFragment implements OnMapReadyCallback {

    private MapView mapHolder;
    private GoogleMapView googleMapView;
    private GoogleMap gMap;

    private LatLng currentLocation;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMapView = new GoogleMapView(getContext(), googleMap);
        gMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMapView.setLocationServices(true);
        }

        googleMapView.getUISettings().setMyLocationButtonEnabled(false);
        googleMapView.getUISettings().setMapToolbarEnabled(false);

        LatLng sanDiego = new LatLng(32.7157, 117.1611);


        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sanDiego, 13));

        googleMap.addMarker(new MarkerOptions()
                .title("San Diego")
                .snippet("wahoooooo")
                .position(sanDiego));

        googleMapView.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                gMap.addMarker(new MarkerOptions()
                        .title("Promotion")
                        .snippet("cash money")
                        .position(latLng));
            }
        });



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

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void setMapHolder(MapView googleMapHolder) {
        this.mapHolder = googleMapHolder;
    }

}
