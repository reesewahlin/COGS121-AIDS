package com.cogs121.ixd.activities.main;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView tvPromoLocation;
    private TextView tvPromoDate;
    private TextView tvPromoDetails;
    private TextView tvPromoTitle;

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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_promo, container, false);
//        locusPoint = getStoreFactory().getLocusPointStore().getLocusPointByLocation(getStoreFactory().getLocationStore().getPromoLocation().toString());
//        tvPromoDate = ViewUtils.getView(rootView, R.id.tv_promo_date);
//        tvPromoDate.setText(locusPoint.getLpDate());
//        tvPromoDetails = ViewUtils.getView(rootView, R.id.tv_promo_details);
//        tvPromoDetails.setText(locusPoint.getLpDetails());
//        tvPromoLocation = ViewUtils.getView(rootView, R.id.tv_promo_location);
//        tvPromoLocation.setText(getLocality());
//        tvPromoTitle = ViewUtils.getView(rootView, R.id.tv_promo_title);
//        tvPromoTitle.setText(locusPoint.getLpTitle());
//
//        closeButton = ViewUtils.getView(rootView, R.id.b_close_promo);
//        closeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getControllerFactory().getNavigationController().transitionToPage(getPage(), Page.MAIN_MAP);
//            }
//        });

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
