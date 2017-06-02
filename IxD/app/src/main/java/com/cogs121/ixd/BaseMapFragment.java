package com.cogs121.ixd;


import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.utils.GoogleMapView;
import com.cogs121.ixd.utils.LocusPoint;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

/**
 * Created by Chad on 4/26/17.
 */

public class BaseMapFragment extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnInfoWindowLongClickListener,
        PlaceSelectionListener, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnMarkerClickListener, GoogleMap.InfoWindowAdapter {

    private MapView mapHolder;
    private GoogleMapView googleMapView;
    private GoogleMap gMap;

    private GoogleApiClient googleApiClient;

    private LatLng currentLocation;

    private Address address;

    private Bitmap smallMarker;

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
        smallMarker = Bitmap.createScaledBitmap(b, 125, 175, false);

        try {
            boolean success = gMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));

            if(!success) {
                Log.e("Tag", "Style parsing failed");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("tag", "didn't load json");
        }

        googleMapView.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (getStoreFactory().getEnterpriseUserStore().isEnterprise()) {
                    gMap.addMarker(new MarkerOptions()
                            .title("Create new promo?")
                            .snippet("").icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                            .position(latLng));
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Only verified accounts can create Locus points").setTitle("Whoops");
                    AlertDialog dialog = builder.create();
                    builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    dialog.show();
                }
                }

        });

        gMap.setOnMarkerClickListener(this);
        gMap.setOnInfoWindowClickListener(this);
        gMap.setInfoWindowAdapter(this);

        Place searchPlace = getStoreFactory().getSearchStore().getSearchPlace();
        if (searchPlace != null) {
            animateToSearchPlace(searchPlace);
        } else {
            animateToCurrLocation(true);
        }
        onRefreshLocusPoints();
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

    @Override
    public void onStop() {
        super.onStop();
        gMap.setOnInfoWindowClickListener(null);
        googleMapView.setOnMapLongClickListener(null);
        gMap.setOnMarkerClickListener(null);
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

    private void animateToSearchPlace(Place place) {
        LatLng latLng = place.getLatLng();
        googleMapView.animateToLocation(latLng, 17f, true);
    }

    private void onRefreshLocusPoints() {
        Map<String, LocusPoint> map = getStoreFactory().getLocusPointStore().getLocusPointList();

        if (map != null) {
            for (Map.Entry<String, LocusPoint> entry : map.entrySet()) {
                LocusPoint lp = entry.getValue();
                gMap.addMarker(new MarkerOptions()
                        .title(lp.getLpTitle())
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
                        .position(lp.getLpPosition()));
            }
        }
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        getStoreFactory().getLocationStore().setPromoLocation(marker.getPosition());
        LocusPoint lp = getStoreFactory().getLocusPointStore().getLocusPointByLocation(marker.getPosition().toString());
        if (lp != null) {
            marker.setTitle(lp.getLpTitle());
        }
        return false;
    }


    @Override
    public View getInfoWindow(Marker marker) {

        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }
}
