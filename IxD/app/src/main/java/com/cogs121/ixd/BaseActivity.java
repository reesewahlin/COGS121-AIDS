package com.cogs121.ixd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cogs121.ixd.Controllers.ControllerFactory;

/**
 * Created by Chad on 4/10/17.
 */

public class BaseActivity extends AppCompatActivity implements ServiceContainer {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ControllerFactory getControllerFactory() {
        return LocusApplication.getInstance().getControllerFactory();
    }

    //TODO: Add all the google maps API stuff into the activity lifecycle callbacks here
}
