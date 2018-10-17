package com.acmes.acmes.mode.request;

import com.acmes.acmes.AcmesAPI;
import com.acmes.acmes.mode.bean.BCollection;
import com.acmes.acmes.mode.response.AcmesResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/2/27.
 */

public class CollectionRequest extends AcmesRequest {

    public static final class Response extends AcmesResponse<List<BCollection>> {

    }

    @Override
    public Observable<Response> callAPI(AcmesAPI api) {
        return api.collections(this);
    }
}
