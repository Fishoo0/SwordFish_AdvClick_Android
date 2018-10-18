package acmes.swordfish.advclick.controler.withdraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.main.MainContentFragment;

/**
 * Created by fishyu on 2018/10/17.
 */

public class WithDrawFragment extends MainContentFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.withdraw_fragment, null);
    }

}
