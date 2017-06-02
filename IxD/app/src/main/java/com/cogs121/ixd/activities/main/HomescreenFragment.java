package com.cogs121.ixd.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.cogs121.ixd.BaseFragment;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;
import com.cogs121.ixd.utils.ViewUtils;

/**
 * Created by Chad on 4/17/17.
 */

public class HomescreenFragment extends BaseFragment {

    public static final String TAG = HomescreenFragment.class.getName();

    private TextView tvSwitchText;

    private Button map, login, signup;

    private Switch userTypeSwitch;

    public HomescreenFragment() {
        page = Page.MAIN_HOME;
    }

    public static HomescreenFragment newInstance() {
        HomescreenFragment fragment = new HomescreenFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_homescreen_new, container, false);

        login = ViewUtils.getView(rootView, R.id.b_homescreen_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getStoreFactory().getEnterpriseUserStore().isEnterprise()) {
                    getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_CREATE_COMPANY);
                    getStoreFactory().getEnterpriseUserStore().setLogin(true);
                }
                else {
                    getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_CREATE_USER);
                    getStoreFactory().getConsumerUserStore().setLogin(true);
                }
            }
        });

        signup = ViewUtils.getView(rootView, R.id.b_homescreen_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getStoreFactory().getEnterpriseUserStore().isEnterprise()) {
                    getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_CREATE_COMPANY);
                    getStoreFactory().getEnterpriseUserStore().setLogin(false);
                }
                else {
                    getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_CREATE_USER);
                    getStoreFactory().getConsumerUserStore().setLogin(false);
                }
            }
        });

        tvSwitchText = ViewUtils.getView(rootView, R.id.tv_homescreen_user_text);
        userTypeSwitch = ViewUtils.getView(rootView, R.id.s_homescreen_user);
        if (getStoreFactory().getEnterpriseUserStore().isEnterprise()) {
            userTypeSwitch.setChecked(true);
            tvSwitchText.setText("Enterprise");
        }
        userTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tvSwitchText.setText("Enterprise");
                    getStoreFactory().getEnterpriseUserStore().setEnterprise(true);
                } else {
                    tvSwitchText.setText("User");
                    getStoreFactory().getEnterpriseUserStore().setEnterprise(false);
                }
            }
        });
        return rootView;
    }
}
