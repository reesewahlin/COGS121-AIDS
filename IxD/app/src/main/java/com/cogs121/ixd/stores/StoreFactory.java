package com.cogs121.ixd.stores;

import android.content.Context;

import com.cogs121.ixd.stores.location.LocationStore;
import com.cogs121.ixd.stores.locuspoint.LocusPointStore;

/**
 * Created by Chad on 4/26/17.
 */

public class StoreFactory {

    private static StoreFactory storeFactory;
    private Context context;

    private LocationStore locationStore;
    private LocusPointStore locusPointStore;

    private static boolean isTornDown = false;

    private StoreFactory(Context context) {
        this.context = context;
    }

    public static StoreFactory getInstance(Context context) {
        if (storeFactory == null) {
            storeFactory = new StoreFactory(context);
        }
        return storeFactory;
    }

    public Context getContext() {
        return context;
    }

    public LocationStore getLocationStore() {
        if (locationStore == null) {
            locationStore = new LocationStore();
        }
        return locationStore;
    }

    public LocusPointStore getLocusPointStore() {
        if (locusPointStore == null) {
            locusPointStore = new LocusPointStore();
        }
        return locusPointStore;
    }

    public void tearDown() {
        reset();
        storeFactory = null;
        isTornDown = true;
    }

    private void reset() {
        locationStore = null;
        locusPointStore = null;
         isTornDown = false;
    }

}
