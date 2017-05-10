package com.cogs121.ixd.stores.locuspoint;

import com.cogs121.ixd.utils.LocusPoint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chad on 5/9/17.
 */

public class LocusPointStore {

    private List<LocusPoint> locusPointList = new ArrayList<>();

    public void addLocusPoint(LocusPoint locusPoint) {
        if (locusPoint == null) {
            return;
        }
        locusPointList.add(locusPoint);
    }

    public void removeLocusPoint(LocusPoint deleteLocusPoint) {
        locusPointList.remove(deleteLocusPoint);
    }


}
