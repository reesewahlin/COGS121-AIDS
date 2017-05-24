package com.cogs121.ixd.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cogs121.ixd.BaseFragment;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;
import com.cogs121.ixd.utils.ViewUtils;

/**
 * Created by Chad on 5/24/17.
 */

public class CreateCompanyUserFragment extends BaseFragment {

    public static final String TAG = CreateCompanyUserFragment.class.getName();

    private EditText companyName;
    private EditText companyEmail;

    private Button signUp;

    public static CreateCompanyUserFragment newInstance() {
        CreateCompanyUserFragment fragment = new CreateCompanyUserFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public CreateCompanyUserFragment() {
        page = Page.MAIN_CREATE_COMPANY;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_company, container, false);

        companyName = ViewUtils.getView(rootView, R.id.et_create_company_name);
        companyEmail = ViewUtils.getView(rootView, R.id.et_create_company_email);

        signUp = ViewUtils.getView(rootView, R.id.b_create_company_confirm);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStoreFactory().getUserStore().createUser(companyEmail.getText().toString(), true);
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_HOME);
            }
        });

        return rootView;
    }
}
