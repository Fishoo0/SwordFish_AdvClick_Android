package com.acmes.acmes;

import com.acmes.simpleandroid.imp.Square.RetrofitSimpleModel;

import retrofit2.Retrofit;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AcmesMode extends RetrofitSimpleModel<AcmesAPI> {

    static Retrofit sRetrofit = AcmesApplication.getInstance().buildRetrofit(AcmesAPI.BASE_URL);

    public AcmesMode() {
        super(sRetrofit, AcmesAPI.class);
    }
}
