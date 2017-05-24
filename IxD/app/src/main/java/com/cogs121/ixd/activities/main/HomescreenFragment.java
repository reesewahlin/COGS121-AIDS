package com.cogs121.ixd.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
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

    private Button map;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_homescreen, container, false);

        map = ViewUtils.getView(rootView, R.id.b_go_map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
            }
        });

        tvSwitchText = ViewUtils.getView(rootView, R.id.tv_homescreen_user_text);
        userTypeSwitch = ViewUtils.getView(rootView, R.id.s_homescreen_user);
        userTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tvSwitchText.setText("Enterprise");
                    getStoreFactory().getEnterpriseUserStore().setEnterprise(true);
                    getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_CREATE_COMPANY);
                } else {
                    tvSwitchText.setText("User");
                    getStoreFactory().getEnterpriseUserStore().setEnterprise(false);
                    getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_CREATE_USER);
                }
            }
        });
        return rootView;
    }
}
