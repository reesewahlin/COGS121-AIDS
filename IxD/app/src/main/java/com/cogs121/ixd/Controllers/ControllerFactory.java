package com.cogs121.ixd.Controllers;

import com.cogs121.ixd.Controllers.navigation.NavigationController;

/**
 * Created by Chad on 4/10/17.
 */

public class ControllerFactory {

    private static ControllerFactory controllerFactory;

    private boolean isTornDown = false;

    private NavigationController navigationController;

    private ControllerFactory() {}

    public static ControllerFactory getInstance() {
        if (controllerFactory == null) {
            controllerFactory = new ControllerFactory();
        }
        return controllerFactory;
    }

    public void reset() {
        navigationController = null;

        isTornDown = false;
    }

    public void tearDown() {
        reset();

        controllerFactory = null;
        isTornDown = true;
    }

    public boolean isTornDown() {
        return isTornDown;
    }

    public NavigationController getNavigationController() {
        if (navigationController == null) {
            navigationController = new NavigationController();
        }
        return navigationController;
    }

}
