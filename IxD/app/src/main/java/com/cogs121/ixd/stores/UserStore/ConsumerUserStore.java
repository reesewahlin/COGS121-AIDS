package com.cogs121.ixd.stores.UserStore;

import com.cogs121.ixd.utils.LocusPoint;
import com.cogs121.ixd.utils.UserAccount;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rjw on 5/24/17.
 */

/*
* Stores the list of all consumer users
*
* */
public class ConsumerUserStore {

    private HashMap<String, UserAccount> userAccountHashMap = new HashMap<>();
    private boolean isLogin = true;
    private String currentUser = "None";

    public void createConsumerUserStore(String username, String password) {
        UserAccount user = new UserAccount(username, password);
        userAccountHashMap.put(username, user);
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public boolean isLogin() {
        return isLogin;
    }


    public HashMap<String, UserAccount> getUserAccountHashMap() {
        return userAccountHashMap;
    }

    public void addUser(String username, String password) {
        UserAccount user = new UserAccount(username, password);
        userAccountHashMap.put(username, user);
    }

    public UserAccount getUser(String username) {
        UserAccount user = userAccountHashMap.get(username);
        return user;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }
}
