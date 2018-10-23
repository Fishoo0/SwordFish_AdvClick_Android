package acmes.swordfish.advclick.controler.main;

import android.util.Log;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import acmes.swordfish.advclick.AdvClickMode;
import acmes.swordfish.advclick.controler.login.IAccountManager;
import acmes.swordfish.advclick.controler.login.SharedPrefAccountManager;
import acmes.swordfish.advclick.mode.bean.BEarn;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.GetEarnRequest;
import acmes.swordfish.advclick.mode.request.UploadEarnRequest;

/**
 * Created by fishyu on 2018/1/2.
 */

public class ClickMode extends AdvClickMode {

    private static ClickMode mLoginMode = new ClickMode();
    private IAccountManager mAccountManager = SharedPrefAccountManager.getInstance();

    public static ClickMode getInstance() {
        return mLoginMode;
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (request instanceof GetEarnRequest || request instanceof UploadEarnRequest) {
            if (response.getData() instanceof BEarn) {
                if (mAccountManager.getCurrentUser() != null) {
                    Log.d(TAG, "BEarn has been updated.");
                    mAccountManager.getCurrentUser().mEarn = (BEarn) response.getData();
                }
            }
        }
    }


    /**
     * Getting current user.
     *
     * @return
     */
    public BUser getUser() {
        return mAccountManager.getCurrentUser();
    }
}
