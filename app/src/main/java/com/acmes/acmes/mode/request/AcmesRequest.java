package com.acmes.acmes.mode.request;

import com.acmes.acmes.AcmesAPI;
import com.acmes.acmes.mode.response.AcmesResponse;
import com.acmes.simpleandroid.imp.Square.RetrofitSimpleRequest;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AcmesRequest<T extends AcmesResponse> extends RetrofitSimpleRequest<T, AcmesAPI> {

    @Override
    public Observable<T> callAPI(AcmesAPI api) {
        return null;
    }
}
