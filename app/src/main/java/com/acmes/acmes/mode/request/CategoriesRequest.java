package com.acmes.acmes.mode.request;

import com.acmes.acmes.AcmesAPI;
import com.acmes.acmes.mode.bean.BCategory;
import com.acmes.acmes.mode.response.AcmesResponse;

import java.io.Serializable;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/2/5.
 */

public class CategoriesRequest extends AcmesRequest {

    public static class Response extends AcmesResponse<List<BCategory>> implements Serializable {

    }


    public CategoriesRequest() {

    }

    @Override
    public Observable<Response> callAPI(AcmesAPI api) {
        return api.categories(this);
    }
}
