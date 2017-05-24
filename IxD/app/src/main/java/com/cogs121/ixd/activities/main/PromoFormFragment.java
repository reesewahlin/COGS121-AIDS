package com.cogs121.ixd.activities.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
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
import com.cogs121.ixd.utils.LocusPoint;
import com.cogs121.ixd.utils.ViewUtils;
import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by rjw on 5/3/17.
 */

public class PromoFormFragment extends BaseFragment {

    public static final String TAG = PromoFormFragment.class.getName();

    private Button createPromo;
    private Button addQrCode;
    private Button closeButton;

    private Uri photoUri;

    private int IMAGE_PICKER_SELECT = 1;

    private EditText etTitle;
    private EditText etDetails;
    private EditText etDate;
    private EditText etLpName;

    private ImageView ivImage;

    private TextView tvLocation;
    private TextView tvAddPhoto;

    private LatLng promoLocation;

    private Address address;



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
        setPromoLocation();
        final Context context = getActivity();;

        tvLocation = ViewUtils.getView(rootView, R.id.tv_promo_form_location);
        tvLocation.setText(getLocality());
        tvAddPhoto = ViewUtils.getView(rootView, R.id.tv_promo_form_add_photo);
        createPromo = ViewUtils.getView(rootView, R.id.b_confirm_promo_form);
        createPromo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String lpName = etLpName.getText().toString();
                String details = etDetails.getText().toString();
                String date = etDate.getText().toString();
                String location = promoLocation.toString();

                LocusPoint lp = new LocusPoint(photoUri, title, lpName, details, date, location);
                lp.createLocusPoint();
                getStoreFactory().getLocusPointStore().addLocusPoint(lp);
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
            }
        });
        addQrCode = ViewUtils.getView(rootView, R.id.b_promo_form_qr_code);
        addQrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence text = "Nice! You created a QR Code!";
                Toast toast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        closeButton = ViewUtils.getView(rootView, R.id.b_promo_form_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
            }
        });

        etTitle = ViewUtils.getView(rootView, R.id.et_promo_form_title);
        etLpName = ViewUtils.getView(rootView, R.id.et_promo_form_lp_name);
        etDetails = ViewUtils.getView(rootView, R.id.et_promo_form_details);
        etDate = ViewUtils.getView(rootView, R.id.et_promo_form_date);

        ivImage = ViewUtils.getView(rootView, R.id.iv_promo_form_image);
        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, IMAGE_PICKER_SELECT);
            }
        });

        return rootView;
    }

    private void setPromoLocation() {
        promoLocation = getStoreFactory().getLocationStore().getPromoLocation();
    }

    private String getLocality() {
        Geocoder geocoder = new Geocoder(getContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(promoLocation.latitude, promoLocation.longitude, 1);
            address = addressList.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address.getAddressLine(0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_PICKER_SELECT
                && resultCode == Activity.RESULT_OK) {
            String path = getPathFromCameraData(data, this.getActivity());
            Log.i("PICTURE", "Path: " + path);
            if (path != null) {
                Uri selectedImageUri = data.getData();
                ivImage.setImageURI(selectedImageUri);
                tvAddPhoto.setText("");
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
