package com.cogs121.ixd.utils;

import java.util.ArrayList;

/**
 * Created by rjw on 6/1/17.
 */

public class UserAccount {

    private String username, password;
    private ArrayList<String> favoriteLocusPoints = new ArrayList<>();


    public UserAccount (String name, String pw) {
        username = name;
        password = pw;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<String> getFavoriteLocusPoints() {
        return favoriteLocusPoints;
    }

    public void addFavorite(LocusPoint lp) {
        if (lp != null) {
            String latlng = lp.getLpLocation();
            favoriteLocusPoints.add(latlng);
        }
    }
}
