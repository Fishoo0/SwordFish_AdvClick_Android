package com.acmes.acmes.mode.request;

import com.acmes.acmes.AcmesAPI;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/3.
 */

public class LogoutRequest extends AcmesRequest {

    public String user_name;
    public String user_password;

    public LogoutRequest(String userName) {
        user_name = userName;
    }

    @Override
    public Observable callAPI(AcmesAPI api) {
        return api.logout(this);
    }

}
