package acmes.swordfish.advclick.controler.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.R;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 */

public class MainActivityNavigation extends AdvClickActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_navigation);

        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_view);
        mBottomNavigationView.inflateMenu(R.menu.main_bottom_nav_items_full);
        mBottomNavigationView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryLight));
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        // init and select the first
        mBottomNavigationView.setSelectedItemId(R.id.with_draw);
    }


    public Fragment getCurrentFragment() {
        switch (mBottomNavigationView.getSelectedItemId()) {
            case R.id.with_draw:
                return getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_with_draw));
            case R.id.function:
                return getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_function));
            case R.id.buy:
                return getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_buy));
            case R.id.profile:
                return getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_profile));
            case R.id.youmeng:
                return getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_youmeng));
        }
        return null;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.with_draw:
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_with_draw)).getView().setVisibility(View.VISIBLE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_function)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_buy)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_profile)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_youmeng)).getView().setVisibility(View.GONE);
                break;
            case R.id.function:
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_with_draw)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_function)).getView().setVisibility(View.VISIBLE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_buy)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_profile)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_youmeng)).getView().setVisibility(View.GONE);
                break;
            case R.id.buy:
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_with_draw)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_function)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_buy)).getView().setVisibility(View.VISIBLE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_profile)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_youmeng)).getView().setVisibility(View.GONE);
                break;
            case R.id.profile:
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_with_draw)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_function)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_buy)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_profile)).getView().setVisibility(View.VISIBLE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_youmeng)).getView().setVisibility(View.GONE);
                break;
            case R.id.youmeng:
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_with_draw)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_function)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_buy)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_profile)).getView().setVisibility(View.GONE);
                getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_youmeng)).getView().setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }

}