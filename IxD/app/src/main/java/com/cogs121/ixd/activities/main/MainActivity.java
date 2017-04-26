package com.cogs121.ixd.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.cogs121.ixd.BaseActivity;
import com.cogs121.ixd.Controllers.navigation.NavigationControllerObserver;
import com.cogs121.ixd.Controllers.navigation.Page;
import com.cogs121.ixd.R;

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

    private void transitionToMapActivity() {
        Intent intent = new Intent();

        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getControllerFactory().getNavigationController().addObserver(this);
        openStartFragment();
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
                fragmentTransaction.replace(R.id.fl_main, HomescreenFragment.newInstance(), HomescreenFragment.TAG);
                fragmentTransaction.commit();
                break;
            case MAIN_TEST:
                fragmentTransaction.replace(R.id.fl_main, TestFragment.newInstance(), TestFragment.TAG);
                fragmentTransaction.commit();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Youuuuu can't do this", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
