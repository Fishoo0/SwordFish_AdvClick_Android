package com.acmes.simpleandroid.imp.Square;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/3.
 */

public abstract class RetrofitSimpleRequest<Response extends RetrofitSimpleResponse, API> extends SimpleRequest<Response> {

    @Override
    public void buildParams(Object someNetEngineMaybe) {

    }

    public abstract Observable<Response> callAPI(API api);

}
