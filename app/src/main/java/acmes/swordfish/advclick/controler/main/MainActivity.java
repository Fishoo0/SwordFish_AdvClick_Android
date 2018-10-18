package acmes.swordfish.advclick.controler.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.R;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 */

public class MainActivity extends AdvClickActivity implements View.OnClickListener {

    private View mCurrentSelection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // init and select the first
        onClick(findViewById(R.id.with_draw));
    }


    @Override
    public void onClick(View v) {
        if (v != mCurrentSelection) {
            getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_with_draw)).getView().setVisibility(View.GONE);
            getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_function)).getView().setVisibility(View.GONE);
            getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_buy)).getView().setVisibility(View.GONE);
            getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_profile)).getView().setVisibility(View.GONE);
            getSupportFragmentManager().findFragmentByTag(getString(R.string.value_tag_youmeng)).getView().setVisibility(View.GONE);

            getSupportFragmentManager().findFragmentByTag((String) v.getTag()).getView().setVisibility(View.VISIBLE);
            mCurrentSelection = v;
        }
    }


    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentByTag((String) mCurrentSelection.getTag());
    }
}