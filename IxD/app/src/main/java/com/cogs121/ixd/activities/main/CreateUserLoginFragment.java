package com.cogs121.ixd.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cogs121.ixd.BaseFragment;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;
import com.cogs121.ixd.utils.ViewUtils;
import com.google.android.gms.vision.text.Text;

/**
     * Created by Chad on 5/24/17.
     */

public class CreateUserLoginFragment extends BaseFragment {

    public static final String TAG = com.cogs121.ixd.activities.main.CreateUserLoginFragment.
                                    class.getName();

    private EditText et_username;
    private EditText et_password;

    private TextView tv_userAddPhoto;
    private ImageView iv_userAddPhoto;

    private Button submitButton;

    public static com.cogs121.ixd.activities.main.CreateUserLoginFragment newInstance() {
        com.cogs121.ixd.activities.main.CreateUserLoginFragment fragment =
                new com.cogs121.ixd.activities.main.CreateUserLoginFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    public CreateUserLoginFragment() {
        page = Page.MAIN_CREATE_USER;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_user, container, false);
        final Boolean isLogin;

        et_username = ViewUtils.getView(rootView, R.id.et_create_user_username);
        et_password = ViewUtils.getView(rootView, R.id.et_create_user_password);

        submitButton = ViewUtils.getView(rootView, R.id.b_create_user_confirm);

        if (isLogin = getStoreFactory().getConsumerUserStore().isLogin())
            submitButton.setText("Log in");
        else
            submitButton.setText("Sign up");

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = et_username.getText().toString().replace(" ", "");

                // user does not exist
                if (getStoreFactory().getConsumerUserStore().getUser(userName) == null) {
                    // logging in
                    if (isLogin) {
                        Toast.makeText(getActivity(), "No account exists with that username!",
                                        Toast.LENGTH_SHORT).show();
                    }
                    // signing up
                    else {
                        getStoreFactory().getConsumerUserStore().addUser(
                                userName, et_password.getText().toString());
                        getStoreFactory().getConsumerUserStore().setCurrentUser(userName);
                        getControllerFactory().getNavigationController().transitionToPage(getPage(),
                                Page.MAIN_MAP);
                    }
                }
                // user exists
                else {
                    // logging in
                    if (isLogin) {
                        getStoreFactory().getConsumerUserStore().setCurrentUser(userName);
                        getControllerFactory().getNavigationController().transitionToPage(getPage(),
                                Page.MAIN_MAP);
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
