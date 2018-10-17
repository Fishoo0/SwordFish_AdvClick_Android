package acmes.swordfish.advclick.controler.withdraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import acmes.swordfish.advclick.AdvClickFragment;
import acmes.swordfish.advclick.R;

/**
 * Created by fishyu on 2018/10/17.
 */

public class WithDrawFragment extends AdvClickFragment {

    public static Fragment getInstance(FragmentManager fragmentManager) {
        Fragment fragment = fragmentManager.findFragmentByTag(WithDrawFragment.class.getSimpleName());
        if (fragment == null) {
            fragment = new WithDrawFragment();
        }
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.withdraw_fragment, null);
    }

}
