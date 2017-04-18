package com.cogs121.ixd.Controllers.navigation;

/**
 * Created by Chad on 4/10/17.
 */

public interface NavigationControllerObserver {
    void onPageTransition(Page fromPage, Page toPage);
}
