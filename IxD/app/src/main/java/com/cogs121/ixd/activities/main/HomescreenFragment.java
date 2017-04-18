package com.cogs121.ixd.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cogs121.ixd.BaseFragment;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;

/**
 * Created by Chad on 4/17/17.
 */

public class HomescreenFragment extends BaseFragment {

    public static final String TAG = HomescreenFragment.class.getName();

    private TextView test;

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
        return rootView;
    }
}
