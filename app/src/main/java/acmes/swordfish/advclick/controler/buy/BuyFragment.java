package acmes.swordfish.advclick.controler.buy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import acmes.swordfish.advclick.AdvClickApplication;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.main.MainContentFragment;
import acmes.swordfish.advclick.mode.AdvClickAPI;
import butterknife.BindView;

/**
 * Created by fishyu on 2018/10/17.
 */

public class BuyFragment extends MainContentFragment {


    @BindView(R.id.imageview)
    ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.buy_fragment, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AdvClickApplication.getPicasso().load(AdvClickAPI.IMAGE_WECHAT_CARD).into(mImageView);
    }
}
