package acmes.swordfish.advclick.controler.main;

import acmes.swordfish.advclick.AdvClickFragment;

/**
 * Created by fishyu on 2018/10/18.
 */

public class MainContentFragment extends AdvClickFragment {


    protected boolean isCurrentFragment() {
        return ((MainActivity) getActivity()).getCurrentFragment() == this;
    }

}
