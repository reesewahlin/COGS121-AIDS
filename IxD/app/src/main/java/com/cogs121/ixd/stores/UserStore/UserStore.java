package com.cogs121.ixd.stores.UserStore;

/**
 * Created by Chad on 5/24/17.
 */

public class UserStore {

    private String userName;
    private String companyName;

    private boolean isEnterprise = false;

    public void createUser(String username, boolean isCompany) {
        if (isCompany) {
            this.companyName = username;
            isEnterprise = true;
        } else {
            this.userName = username;
        }
    }

    public boolean isEnterprise() {
        return isEnterprise;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
