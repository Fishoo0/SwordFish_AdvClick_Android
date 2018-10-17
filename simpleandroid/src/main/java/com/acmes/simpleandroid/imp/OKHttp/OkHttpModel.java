package com.acmes.simpleandroid.imp.OKHttp;

import com.acmes.simpleandroid.mvc.ISimpleModeCallback;
import com.acmes.simpleandroid.mvc.SimpleApplication;
import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;

import okhttp3.OkHttpClient;

/**
 * Created by fishyu on 2017/9/7.
 */

public class OkHttpModel extends SimpleModel {

    OkHttpClient mOkHttpClient;

    public OkHttpModel(OkHttpClient okHttpClient) {
        super();
    }

    @Override
    protected void onCancelRequest(Object request, Object callable) {

    }

    @Override
    public void performRequest(SimpleRequest request, ISimpleModeCallback callback) {

    }

}
