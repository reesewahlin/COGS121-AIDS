package com.cogs121.ixd;

import com.cogs121.ixd.Controllers.ControllerFactory;
import com.cogs121.ixd.stores.StoreFactory;

/**
 * Created by Chad on 4/17/17.
 */

public interface ServiceContainer {
    ControllerFactory getControllerFactory();

    StoreFactory getStoreFactory();
}
