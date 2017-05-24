package com.cogs121.ixd.stores.UserStore;

/**
 * Created by Chad on 5/24/17.
 */

public class EnterpriseUserStore {

    private String userName;
    private String companyName;

    private boolean isEnterprise = false;

    public void createEnterpriseUser(String username, boolean isCompany) {
        if (isCompany) {
            this.companyName = username;
            isEnterprise = true;
        } else {
            this.userName = username;
        }
    }

    public void setEnterprise(boolean enterprise) {
        isEnterprise = enterprise;
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
