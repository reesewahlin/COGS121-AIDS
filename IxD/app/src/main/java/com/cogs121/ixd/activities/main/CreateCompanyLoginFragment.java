package com.cogs121.ixd.activities.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
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

/**
 * Created by Chad on 5/24/17.
 */

public class CreateCompanyLoginFragment extends BaseFragment {

    public static final String TAG = CreateCompanyLoginFragment.class.getName();

    private EditText password;
    private EditText et_companyEmail, et_companyName;
    private TextView tv_companyName, tv_companyAddPhoto;

    private Uri photoUri;

    private ImageView iv_companyPhoto;

    private Button submitButton;

    private int IMAGE_PICKER_SELECT = 1;


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
        tv_companyAddPhoto = ViewUtils.getView(rootView, R.id.tv_user_add_photo);
        submitButton = ViewUtils.getView(rootView, R.id.b_create_company_confirm);
        iv_companyPhoto = ViewUtils.getView(rootView, R.id.iv_company_photo);

        if (isLogin = getStoreFactory().getEnterpriseUserStore().isLogin()) {
            submitButton.setText("Log in");
            iv_companyPhoto.setVisibility(View.INVISIBLE);
            tv_companyAddPhoto.setVisibility(View.INVISIBLE);
        } else {
            submitButton.setText("Sign up");
            iv_companyPhoto.setVisibility(View.VISIBLE);
            tv_companyAddPhoto.setVisibility(View.VISIBLE);

        }

        tv_companyAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Clicked!!", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, IMAGE_PICKER_SELECT);
            }
        });


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




    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_PICKER_SELECT
                && resultCode == Activity.RESULT_OK) {
            String path = getPathFromCameraData(data, this.getActivity());
            Log.i("PICTURE", "Path: " + path);
            if (path != null) {
                Uri selectedImageUri = data.getData();
                iv_companyPhoto.setImageURI(selectedImageUri);
                tv_companyAddPhoto.setVisibility(View.INVISIBLE);
                iv_companyPhoto.setVisibility(View.VISIBLE);
                photoUri = selectedImageUri;
            }
        }
    }

    public static String getPathFromCameraData(Intent data, Context context) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }











}
