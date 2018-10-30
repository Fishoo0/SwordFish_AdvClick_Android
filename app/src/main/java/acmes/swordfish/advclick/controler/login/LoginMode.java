package acmes.swordfish.advclick.controler.login;

import android.text.TextUtils;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import acmes.swordfish.advclick.AdvClickMode;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.LoginRequest;
import acmes.swordfish.advclick.mode.request.LogoutRequest;
import acmes.swordfish.advclick.mode.request.RegisterRequest;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginMode extends AdvClickMode {

    private static LoginMode mLoginMode = new LoginMode();
    public IAccountManager mAccountManager = SharedPrefAccountManager.getInstance();

    public static LoginMode getInstance() {
        return mLoginMode;
    }

    /**
     * Login
     */
    public void login(LoginRequest loginRequest) {
        performRequest(loginRequest);
    }

    /**
     * Register
     */
    public void register(RegisterRequest request) {
        performRequest(request);
    }

    /**
     * Logout
     *
     * @param request
     */
    public void logout(LogoutRequest request) {
        performRequest(request);
    }


    @Override
    public void onRequestStart(SimpleRequest request) {
        // do it first
        if (request instanceof LogoutRequest) {
            mAccountManager.removeUser(null);
        }
        super.onRequestStart(request);
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        // do it first
        if (response.isSuccess() && response.getData() != null) {
            if (request instanceof LoginRequest && response.getData() instanceof BUser) {
                if (!TextUtils.isEmpty(((LoginRequest) request).password)) {
                    mAccountManager.addUser((BUser) response.getData());
                }
            } else if (request instanceof RegisterRequest && response.getData() instanceof BUser) {
                //do nothing
            }
        }
        super.onResponse(request, response);
    }

    @Override
    public void onDestroy() {
        //single instance ,we do nothing since this method would
    }
}
