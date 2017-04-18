package com.cogs121.ixd;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.cogs121.ixd.Controllers.ControllerFactory;
import com.cogs121.ixd.Controllers.navigation.Page;

/**
 * Created by Chad on 4/10/17.
 */

public class BaseFragment extends Fragment implements ServiceContainer {

    protected Page page;

    public Page getPage() {
        return page;
    }

    @Override
    public ControllerFactory getControllerFactory() {
        return getActivity() != null ? ((BaseActivity) getActivity()).getControllerFactory() : null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
