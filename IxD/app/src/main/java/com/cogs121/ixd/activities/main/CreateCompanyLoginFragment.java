package com.cogs121.ixd.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    private EditText et_companyEmail, et_companyName;
    private TextView tv_companyName;

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
        final Boolean isLogin;

        password = ViewUtils.getView(rootView, R.id.et_create_company_password);
        et_companyEmail = ViewUtils.getView(rootView, R.id.et_create_company_email);
        et_companyName = ViewUtils.getView(rootView, R.id.et_create_company_name);
        tv_companyName = ViewUtils.getView(rootView, R.id.tv_create_company_name);

        submitButton = ViewUtils.getView(rootView, R.id.b_create_company_confirm);

        if (isLogin = getStoreFactory().getEnterpriseUserStore().isLogin()) {
            submitButton.setText("Log in");
        } else {
            submitButton.setText("Sign up");
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String companyName = et_companyName.getText().toString().replace(" ", "");
                String companyEmail = et_companyEmail.getText().toString();

                // COMPANY DOESNT EXIST
                if (getStoreFactory().getEnterpriseUserStore().getEnterpriseUser(companyName) == null) {
                    // logging in
                    if (isLogin) {
                        Toast.makeText(getActivity(), "No account exists with that username!", Toast.LENGTH_SHORT).show();
                    // signing up
                    } else {
                        getStoreFactory().getEnterpriseUserStore().createEnterpriseUser(companyEmail, companyName);
                        getStoreFactory().getEnterpriseUserStore().setCurrentUser(companyName);
                        getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
                    }
                // COMPANY EXISTS
                } else {
                    // logging in
                    if (isLogin) {
                        getStoreFactory().getEnterpriseUserStore().setCurrentUser(companyName);
                        getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
                    }
                    // signing up
                    else {
                        Toast.makeText(getActivity(), "An account with that username already exists!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return rootView;
    }
}
