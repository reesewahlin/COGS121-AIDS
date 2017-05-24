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

public class CreateUserLoginFragment extends BaseFragment {

    public static final String TAG = com.cogs121.ixd.activities.main.CreateUserLoginFragment.class.getName();

    private EditText username;
    private EditText password;

    private Button signUp;

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

        username = ViewUtils.getView(rootView, R.id.et_create_user_username);
        password = ViewUtils.getView(rootView, R.id.et_create_user_password);

        signUp = ViewUtils.getView(rootView, R.id.b_create_user_confirm);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getStoreFactory().getConsumerUserStore().createConsumerUser(
                        username.getText().toString(), password.getText().toString());
                getControllerFactory().getNavigationController().transitionToPage(getPage(),
                        Page.MAIN_HOME);
            }
        });

        return rootView;
    }


}
