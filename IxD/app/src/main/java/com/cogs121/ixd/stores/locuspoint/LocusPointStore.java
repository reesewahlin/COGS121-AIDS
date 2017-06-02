package com.cogs121.ixd.stores.locuspoint;

import android.net.Uri;

import com.cogs121.ixd.utils.LocusPoint;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chad on 5/9/17.
 */

public class LocusPointStore {

    private HashMap<String, LocusPoint> locusPointList = new HashMap<>();

    public void addLocusPoint(LocusPoint locusPoint) {
        if (locusPoint == null) {
            return;
        }
        String latlng = locusPoint.getLpLocation();
        locusPointList.put(latlng, locusPoint);
    }

    public void removeLocusPoint(LocusPoint deleteLocusPoint) {
        locusPointList.remove(deleteLocusPoint);
    }

    public LocusPoint getLocusPointByLocation(String location) {
        return locusPointList.get(location);
    }

    public Map<String, LocusPoint> getLocusPointList() {
        return locusPointList;
    }


    //Uri photoUri, String title, String lpName, String details, String date, String location, LatLng position
//    public void initialLocusPoints() {
//        locusPointList.put("37.7459192,-119.53319920000001", new LocusPoint(null, "Half Dome", "REI Half Dome 20% Off", "Hike to the top of half dome and recieve this promotion", "01-31-2018", "37.7459192,-119.53319920000001", new LatLng(37.7459192,-119.53319920000001) ));
//    }

}
