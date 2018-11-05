package acmes.swordfish.advclick.controler.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import acmes.swordfish.advclick.SwordFishActivity;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.LogoutRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.utils.Utils;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LogoutActivity extends SwordFishActivity<LoginMode> {


    public static final void jumpToThis(Context context) {
        Intent intent = new Intent(context, LogoutActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected LoginMode createModel() {
        return LoginMode.getInstance();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BUser user = SharedPrefAccountManager.getInstance().getCurrentUser();
        if (user != null) {
            getModel().performRequest(new LogoutRequest(user.mUserName));
        } else {
            onRequestStart(null);
        }
    }

    @Override
    public void onRequestStart(SimpleRequest request) {
        super.onRequestStart(request);

        // jump to LoginDispatchActivity
        DispatcherActivity.jumpToThis(this);
        finish();

        Utils.showToast("U have been logout successfully!");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
