package com.cogs121.ixd.utils;

/**
 * Created by Chad on 5/9/17.
 */

public class LocusPoint {

    private String lpTitle;
    private String lpDetails;
    private String lpDate;



    private String lpLocation;

    private boolean isCreate = false;

    public LocusPoint(String title, String details, String date, String location) {
        this.lpTitle = title;
        this.lpDate = date;
        this.lpDetails = details;
        this.lpLocation = location;
    }

    public void setLpTitle(String title) {
        this.lpTitle = title;
    }

    public String getLpTitle() {
        return lpTitle;
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
