package com.cogs121.ixd.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cogs121.ixd.BaseFragment;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;
import com.cogs121.ixd.utils.ViewUtils;

/**
 * Created by rjw on 5/3/17.
 */

public class PromoFormFragment extends BaseFragment {

    public static final String TAG = PromoFormFragment.class.getName();

    private Button createPromo;

    public PromoFormFragment() {
        page = Page.MAIN_PROMO_FORM;
    }

    public static PromoFormFragment newInstance() {
        PromoFormFragment fragment = new PromoFormFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_promo_form, container, false);

        createPromo = ViewUtils.getView(rootView, R.id.b_confirm_promo_form);
        createPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
            }
        });
        
        return rootView;
    }


}
