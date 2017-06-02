package com.cogs121.ixd.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.cogs121.ixd.BaseActivity;
import com.cogs121.ixd.Controllers.navigation.NavigationControllerObserver;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;
import com.cogs121.ixd.activities.SearchActivity;

public class MainActivity extends BaseActivity implements NavigationControllerObserver {

    private final static Page START_PAGE = Page.MAIN_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    private void openStartFragment() {
        Page currentPage = getControllerFactory().getNavigationController().getCurrentPage();
        getControllerFactory().getNavigationController().transitionToPage(currentPage, START_PAGE);
    }

    private void transitionToSearchActivity() {
        getControllerFactory().getNavigationController().removeObserver(this);
        Intent intent = new Intent(this, SearchActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void openMapFragment() {
        Page currentPage = getControllerFactory().getNavigationController().getCurrentPage();
        getControllerFactory().getNavigationController().transitionToPage(currentPage, Page.MAIN_MAP);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getControllerFactory().getNavigationController().addObserver(this);
        if (getControllerFactory().getNavigationController().getCurrentPage() == Page.NO_PAGE) {
            openStartFragment();
        } else if ( getControllerFactory().getNavigationController().getCurrentPage() == Page.SEARCH) {
            openMapFragment();
        }
    }

    @Override
    protected void onStop() {
        if (getControllerFactory() != null) {
            getControllerFactory().getNavigationController().removeObserver(this);
        }
        super.onStop();
    }


    @Override
    public void onPageTransition(Page fromPage, Page toPage) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch(toPage) {
            case MAIN_HOME:
                switch(fromPage) {
                    case NO_PAGE:
                        fragmentTransaction.add(R.id.fl_main, HomescreenFragment.newInstance(), HomescreenFragment.TAG);
                        break;
                    case MAIN_MAP:
                        fragmentTransaction.add(R.id.fl_main, HomescreenFragment.newInstance(), HomescreenFragment.TAG);
                        break;
                    case MAIN_CREATE_COMPANY:
                        getSupportFragmentManager().popBackStack();
                        return;
                    case MAIN_CREATE_USER:
                        getSupportFragmentManager().popBackStack();
                        return;
                }
                break;
            case MAIN_TEST:
                fragmentTransaction.add(R.id.fl_main, TestFragment.newInstance(), TestFragment.TAG);
                break;
            case MAIN_MAP:
                switch (fromPage) {
//                    case MAIN_HOME:
//                        fragmentTransaction.setCustomAnimations(R.anim.slide_bottom_to_center, R.anim.none, R.anim.none, R.anim.slide_center_to_bottom);
//                        fragmentTransaction.add(R.id.fl_main, MapFragment.newInstance(), MapFragment.TAG);
//                        break;
                    case MAIN_CREATE_COMPANY:
                        fragmentTransaction.setCustomAnimations(R.anim.slide_bottom_to_center, R.anim.none, R.anim.none, R.anim.slide_center_to_bottom);
                        fragmentTransaction.replace(R.id.fl_main, MapFragment.newInstance(), MapFragment.TAG);
                        break;
                    case MAIN_CREATE_USER:
                        fragmentTransaction.setCustomAnimations(R.anim.slide_bottom_to_center, R.anim.none, R.anim.none, R.anim.slide_center_to_bottom);
                        fragmentTransaction.replace(R.id.fl_main, MapFragment.newInstance(), MapFragment.TAG);
                        break;
                    case SEARCH:
                        fragmentTransaction.setCustomAnimations(R.anim.slide_center_to_right, R.anim.none, R.anim.none, R.anim.slide_right_to_center);
                        fragmentTransaction.add(R.id.fl_main, MapFragment.newInstance(), MapFragment.TAG);
                        break;
                    case MAIN_FAVORITES:
                        getSupportFragmentManager().popBackStack();
                        return;
                    case MAIN_PROMO:
                        getSupportFragmentManager().popBackStack();
                        return;
                    case MAIN_PROMO_FORM:
                        getSupportFragmentManager().popBackStack();
                        return;
                }
                break;
            case MAIN_PROMO:
                fragmentTransaction.setCustomAnimations(R.anim.slide_bottom_to_center, R.anim.none, R.anim.none, R.anim.slide_center_to_bottom);
                fragmentTransaction.add(R.id.fl_main, PromoFragment.newInstance(), PromoFragment.TAG);
                break;
            case MAIN_PROMO_FORM:
                fragmentTransaction.setCustomAnimations(R.anim.slide_bottom_to_center, R.anim.none, R.anim.none, R.anim.slide_center_to_bottom);
                fragmentTransaction.add(R.id.fl_main, PromoFormFragment.newInstance(), PromoFormFragment.TAG);
                break;
            case MAIN_CREATE_COMPANY:
                fragmentTransaction.setCustomAnimations(R.anim.slide_bottom_to_center, R.anim.none, R.anim.none, R.anim.slide_center_to_bottom);
                fragmentTransaction.add(R.id.fl_main, CreateCompanyLoginFragment.newInstance(), CreateCompanyLoginFragment.TAG);
                break;
            case MAIN_CREATE_USER:
                fragmentTransaction.setCustomAnimations(R.anim.slide_bottom_to_center, R.anim.none, R.anim.none, R.anim.slide_center_to_bottom);
                fragmentTransaction.add(R.id.fl_main, CreateUserLoginFragment.newInstance(), CreateUserLoginFragment.TAG);
                break;
            case MAIN_FAVORITES:
                fragmentTransaction.setCustomAnimations(R.anim.slide_right_to_center, R.anim.none, R.anim.none, R.anim.slide_center_to_right);
                fragmentTransaction.add(R.id.fl_main, FavoritesFragment.newInstance(), FavoritesFragment.TAG);
                break;
            case SEARCH:
                transitionToSearchActivity();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Youuuuu can't do this", Toast.LENGTH_LONG).show();
                break;
        }
        fragmentTransaction.addToBackStack(toPage.name());
        fragmentTransaction.commit();
    }

}
