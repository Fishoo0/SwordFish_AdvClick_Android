package com.acmes.acmes.mode.request;

import com.acmes.acmes.AcmesAPI;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class RegisterRequest extends AcmesRequest {

    public String user_name;
    public String user_password;


    public RegisterRequest(String userName, String userPassword) {
        user_name = userName;
        user_password = userPassword;
    }


    @Override
    public Observable callAPI(AcmesAPI api) {
        return api.register(this);
    }
}
