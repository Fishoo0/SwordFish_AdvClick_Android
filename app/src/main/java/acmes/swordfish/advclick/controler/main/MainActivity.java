package acmes.swordfish.advclick.controler.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.buy.BuyFragment;
import acmes.swordfish.advclick.controler.function.FunctionFragment;
import acmes.swordfish.advclick.controler.profile.ProfileFragment;
import acmes.swordfish.advclick.controler.withdraw.WithDrawFragment;
import acmes.swordfish.advclick.controler.youmeng.YouMengFragment;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 */

public class MainActivity extends AdvClickActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        // open first
        onClick(findViewById(R.id.with_draw));
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction transition = getSupportFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.with_draw:
                transition.replace(R.id.fragment_container, WithDrawFragment.getInstance(getSupportFragmentManager()), WithDrawFragment.class.getSimpleName());
                break;
            case R.id.function:
                transition.replace(R.id.fragment_container, FunctionFragment.getInstance(getSupportFragmentManager()), FunctionFragment.class.getSimpleName());
                break;
            case R.id.buy:
                transition.replace(R.id.fragment_container, BuyFragment.getInstance(getSupportFragmentManager()), BuyFragment.class.getSimpleName());
                break;
            case R.id.profile:
                transition.replace(R.id.fragment_container, ProfileFragment.getInstance(getSupportFragmentManager()), ProfileFragment.class.getSimpleName());
                break;
            case R.id.youmeng:
                transition.replace(R.id.fragment_container, YouMengFragment.getInstance(getSupportFragmentManager()), YouMengFragment.class.getSimpleName());
                break;
        }
        transition.commitAllowingStateLoss();
    }
}