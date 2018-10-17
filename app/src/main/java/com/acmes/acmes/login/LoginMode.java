package com.acmes.acmes.login;

import android.text.TextUtils;

import com.acmes.acmes.AcmesMode;
import com.acmes.acmes.mode.bean.BUser;
import com.acmes.acmes.mode.request.LoginRequest;
import com.acmes.acmes.mode.request.LogoutRequest;
import com.acmes.acmes.mode.request.RegisterRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginMode extends AcmesMode {

    private static LoginMode mLoginMode = new LoginMode();
    private IAccountManager mAccountManager = SharedPrefAccountManager.getInstance();

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
            BUser user = new BUser(((LogoutRequest) request).user_name, ((LogoutRequest) request).user_password);
            mAccountManager.removeUser(user);
        }

        super.onRequestStart(request);
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        // do it first
        if (response.isSuccess() && response.getData() != null) {
            if (request instanceof LoginRequest && response.getData() instanceof BUser) {
                BUser user = new BUser(((LoginRequest) request).user_name, ((LoginRequest) request).user_password);
                if (!TextUtils.isEmpty(((LoginRequest) request).user_password)) {
                    mAccountManager.addUser(user);
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
