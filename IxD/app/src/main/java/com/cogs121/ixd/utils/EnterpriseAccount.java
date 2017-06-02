package com.cogs121.ixd.utils;

import java.util.ArrayList;

/**
 * Created by rjw on 6/1/17.
 */

public class EnterpriseAccount {

    private String companyName, companyEmail;
    private ArrayList<String> companyLocusPoints = new ArrayList<>();


    public EnterpriseAccount(String name, String email) {
        companyName = name;
        companyEmail = email;
    }


    public String getCompanyName() {
        return companyName;
    }

    public ArrayList<String> getCompanyLocusPoints() {
        return companyLocusPoints;
    }

    public void addLocusPoint(LocusPoint lp) {
        if (lp != null) {
            String latlng = lp.getLpLocation();
            companyLocusPoints.add(latlng);
        }
    }





}
