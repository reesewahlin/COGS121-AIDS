package com.cogs121.ixd;

import android.app.Application;

import com.cogs121.ixd.Controllers.ControllerFactory;
import com.cogs121.ixd.stores.StoreFactory;

/**
 * Created by Chad on 4/17/17.
 */

public class LocusApplication extends Application implements ServiceContainer {

    public static final String TAG = LocusApplication.class.getName();

    private static LocusApplication locusApplication = null;
    private boolean tornDown = false;

    private ControllerFactory controllerFactory;
    private StoreFactory storeFactory;

    public static LocusApplication getInstance() {
        if (locusApplication == null) {
            throw new RuntimeException("App not initialized");
        }
        if(locusApplication.isTornDown()) {
            locusApplication.init();
        }
        return locusApplication;
    }

    public boolean isTornDown(){ return tornDown; }

    @Override
    public ControllerFactory getControllerFactory() {
        return controllerFactory;
    }

    @Override
    public StoreFactory getStoreFactory() {
        return storeFactory;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        locusApplication = this;
        controllerFactory = ControllerFactory.getInstance();
        storeFactory = StoreFactory.getInstance(getApplicationContext());

        tornDown = false;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        getControllerFactory().tearDown();

        controllerFactory = null;

        tornDown = true;

    }

}
