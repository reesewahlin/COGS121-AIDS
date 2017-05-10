package com.cogs121.ixd.utils;

/**
 * Created by Chad on 5/9/17.
 */

public class LocusPoint {

    private String lpTitle;
    private String lpDetails;
    private String lpDate;

    private boolean isCreate = false;

    public LocusPoint(String title, String details, String date) {
        this.lpTitle = title;
        this.lpDate = date;
        this.lpDetails = details;
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
}
