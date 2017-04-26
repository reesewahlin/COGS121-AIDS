package com.cogs121.ixd.stores;

import android.content.Context;

import com.cogs121.ixd.stores.location.LocationStore;

/**
 * Created by Chad on 4/26/17.
 */

public class StoreFactory {

    private static StoreFactory storeFactory;
    private Context context;

    private LocationStore locationStore;

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

    public void tearDown() {
        reset();
        storeFactory = null;
        isTornDown = true;
    }

    private void reset() {
        locationStore = null;
         isTornDown = false;
    }

}
