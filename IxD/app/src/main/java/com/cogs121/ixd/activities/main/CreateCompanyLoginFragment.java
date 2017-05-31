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

public class CreateCompanyLoginFragment extends BaseFragment {

    public static final String TAG = CreateCompanyLoginFragment.class.getName();

    private EditText password;
    private EditText companyEmail;

    private Button submitButton;

    public static CreateCompanyLoginFragment newInstance() {
        CreateCompanyLoginFragment fragment = new CreateCompanyLoginFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public CreateCompanyLoginFragment() {
        page = Page.MAIN_CREATE_COMPANY;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_company, container, false);

        password = ViewUtils.getView(rootView, R.id.et_create_company_password);
        companyEmail = ViewUtils.getView(rootView, R.id.et_create_company_email);

        submitButton = ViewUtils.getView(rootView, R.id.b_create_company_confirm);

        if (getStoreFactory().getEnterpriseUserStore().isLogin()) {
            submitButton.setText("Log in");
        } else {
            submitButton.setText("Sign up");
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStoreFactory().getEnterpriseUserStore().createEnterpriseUser(companyEmail.getText().toString(), true);
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
            }
        });

        return rootView;
    }
}
