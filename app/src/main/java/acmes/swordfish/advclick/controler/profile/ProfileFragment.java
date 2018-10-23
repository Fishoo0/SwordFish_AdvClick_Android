package acmes.swordfish.advclick.controler.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.login.DispatcherActivity;
import acmes.swordfish.advclick.controler.main.MainContentFragment;

/**
 * Created by fishyu on 2018/10/17.
 */

public class ProfileFragment extends MainContentFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set listeners
//        view.findViewById(R.id.logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                DispatcherActivity.jumpToThis(getActivity(), DispatcherActivity.CMD_LOGOUT);
                break;
        }
    }
}
