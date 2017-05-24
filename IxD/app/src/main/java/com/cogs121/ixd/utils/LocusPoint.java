package com.cogs121.ixd.utils;

import android.net.Uri;

/**
 * Created by Chad on 5/9/17.
 */

public class LocusPoint {

    private String lpTitle;
    private String lpName;
    private String lpDetails;
    private String lpDate;
    private String lpLocation;

    private Uri photoUri;

    private boolean isCreate = false;


    public LocusPoint(Uri photoUri, String title, String lpName, String details, String date, String location) {
        this.photoUri = photoUri;
        this.lpTitle = title;
        this.lpName = lpName;
        this.lpDate = date;
        this.lpDetails = details;
        this.lpLocation = location;
    }


    public Uri getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        this.photoUri = photoUri;
    }

    public void setLpTitle(String title) {
        this.lpTitle = title;
    }

    public String getLpTitle() {
        return lpTitle;
    }

    public String getLpName() {
        return lpName;
    }

    public void setLpName(String lpName) {
        this.lpName = lpName;
    }

    public void setLpDetails(String details) {
        this.lpDetails = details;
    }

    public String getLpDetails() {
        return lpDetails;
    }

    public void setLpDate(String date) {
        this.lpDate = date;
    }

    public String getLpDate() {
        return lpDate;
    }

    public String getLpLocation() {
        return lpLocation;
    }

    public void setLpLocation(String lpLocation) {
        this.lpLocation = lpLocation;
    }

    public void createLocusPoint() {
        isCreate = true;
    }

    public boolean isCreated() {
        return isCreate;
    }

}
