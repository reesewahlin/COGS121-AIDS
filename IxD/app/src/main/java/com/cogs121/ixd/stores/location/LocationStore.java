package com.cogs121.ixd.stores.location;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Chad on 4/26/17.
 */

public class LocationStore {

    private LatLng currentLocation = null;
    private Location lastKnownLocation;

    public LatLng getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location androidLocation) {
        this.currentLocation = new LatLng(androidLocation.getLatitude(), androidLocation.getLongitude());
        this.lastKnownLocation = androidLocation;
    }

}
