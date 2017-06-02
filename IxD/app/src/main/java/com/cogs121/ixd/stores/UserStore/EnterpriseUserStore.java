package com.cogs121.ixd.stores.UserStore;

import android.util.Log;

import com.cogs121.ixd.utils.EnterpriseAccount;
import com.cogs121.ixd.utils.LocusPoint;
import com.cogs121.ixd.utils.UserAccount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Chad on 5/24/17.
 */

public class EnterpriseUserStore {

    private HashMap<String, EnterpriseAccount> enterpriseAccountHashMap = new HashMap<>();
    private boolean isEnterprise = false;
    private boolean isLogin = false;
    private String currentUser = "None";

    public void createEnterpriseUser(String email, String name) {
            EnterpriseAccount ent = new EnterpriseAccount(name, email);
            enterpriseAccountHashMap.put(name, ent);
            isEnterprise = true;
    }

    public void setEnterprise(boolean enterprise) {
        isEnterprise = enterprise;
    }

    public boolean isEnterprise() {
        return isEnterprise;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isLogin() {
        return isLogin;
    }


    public HashMap<String, EnterpriseAccount> getEnterpriseAccountHashMap() {
        return enterpriseAccountHashMap;
    }

    public void addEnterpriseUser(String name, String email) {
        EnterpriseAccount ent = new EnterpriseAccount(name, email);
        enterpriseAccountHashMap.put(name, ent);
    }

    public EnterpriseAccount getEnterpriseUser(String name) {
        EnterpriseAccount ent = enterpriseAccountHashMap.get(name);
        return ent;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }



}
