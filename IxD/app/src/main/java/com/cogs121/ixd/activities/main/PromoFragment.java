package com.cogs121.ixd.activities.main;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cogs121.ixd.BaseFragment;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;
import com.cogs121.ixd.utils.LocusPoint;
import com.cogs121.ixd.utils.ViewUtils;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by rjw on 4/26/17.
 */

public class PromoFragment extends BaseFragment {

    public static final String TAG = PromoFragment.class.getName();

    private LocusPoint locusPoint;

    private Button closeButton;
    private Button saveButton;
    private Button favoriteButton;

    private TextView tvPromoLocation;
    private TextView tvPromoDate;
    private TextView tvPromoDetails;
    private TextView tvPromoTitle;
    private TextView tvPromoLpName;
    private TextView tvPromoPhoto;

    private ImageView ivImage;

    private Address address;

    public PromoFragment() {
        page = Page.MAIN_PROMO;
    }

    public static PromoFragment newInstance() {
        PromoFragment fragment = new PromoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_promo, container, false);
        final Context context = getActivity();;

        //extract locus point
        locusPoint = getStoreFactory().getLocusPointStore().getLocusPointByLocation(getStoreFactory().
                     getLocationStore().getPromoLocation().toString());

        //text fields
        tvPromoDate = ViewUtils.getView(rootView, R.id.tv_promo_date);
        tvPromoDate.setText(locusPoint.getLpDate());

        tvPromoDetails = ViewUtils.getView(rootView, R.id.tv_promo_details);
        tvPromoDetails.setText(locusPoint.getLpDetails());

        tvPromoLocation = ViewUtils.getView(rootView, R.id.tv_promo_location);
        tvPromoLocation.setText(getLocality());

        tvPromoTitle = ViewUtils.getView(rootView, R.id.tv_promo_company_name);
        tvPromoTitle.setText(locusPoint.getLpTitle());

        tvPromoLpName = ViewUtils.getView(rootView, R.id.tv_promo_lp_name);
        tvPromoLpName.setText(locusPoint.getLpName());

        ivImage = ViewUtils.getView(rootView, R.id.iv_form_image);
        ivImage.setImageURI(locusPoint.getPhotoUri());

        tvPromoPhoto = ViewUtils.getView(rootView, R.id.tv_promo_photo_text);
        tvPromoPhoto.setText("");



        //Buttons
        favoriteButton = ViewUtils.getView(rootView, R.id.b_promo_favorite);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //add to account user store
                getStoreFactory().getConsumerUserStore().getUser(
                        getStoreFactory().getConsumerUserStore().getCurrentUser()
                ).addFavorite(locusPoint);
                CharSequence text = "Locus Point saved!";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.show();

            }
        });
//        saveButton = ViewUtils.getView(rootView, R.id.b_promo_save);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //save locus point location to an array list in user store
//                CharSequence text = "Locus Point Saved!";
//                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        });
        closeButton = ViewUtils.getView(rootView, R.id.b_promo_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
            }
        });

        return rootView;
    }

    private String getLocality() {
        LatLng latLng = getStoreFactory().getLocationStore().getPromoLocation();
        Geocoder geocoder = new Geocoder(getContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            address = addressList.get(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address.getAddressLine(0);
    }

}
