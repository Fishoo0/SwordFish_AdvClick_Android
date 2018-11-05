package acmes.swordfish.advclick.controler.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.SwordFishActivity;
import acmes.swordfish.advclick.controler.login.SharedPrefAccountManager;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 */

public class MainActivity extends SwordFishActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }


    @Override
    public void onBackPressed() {
        if (SharedPrefAccountManager.getInstance().getCurrentUser() != null && SharedPrefAccountManager.getInstance().getCurrentUser().isAdmin()) {
            super.onBackPressed();
        } else {
            Utils.backToLauncher(this);
        }
    }


}