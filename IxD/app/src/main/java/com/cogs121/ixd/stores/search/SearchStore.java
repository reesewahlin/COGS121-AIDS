package com.cogs121.ixd.stores.search;

import com.google.android.gms.location.places.Place;

/**
 * Created by Chad on 5/30/17.
 */

public class SearchStore {

    private Place searchPlace;

    public Place getSearchPlace() {
        return searchPlace;
    }

    public void setSearchPlace(Place searchPlace) {
        this.searchPlace = searchPlace;
    }
}
