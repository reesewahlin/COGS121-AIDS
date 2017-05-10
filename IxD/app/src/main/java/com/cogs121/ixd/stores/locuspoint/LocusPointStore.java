package com.cogs121.ixd.stores.locuspoint;

import com.cogs121.ixd.utils.LocusPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chad on 5/9/17.
 */

public class LocusPointStore {

    private Map<String, LocusPoint> locusPointList = new HashMap<>();

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


}
