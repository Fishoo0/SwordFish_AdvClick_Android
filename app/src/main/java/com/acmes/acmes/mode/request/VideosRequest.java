package com.acmes.acmes.mode.request;

import com.acmes.acmes.AcmesAPI;
import com.acmes.acmes.mode.bean.BVideo;
import com.acmes.acmes.mode.response.AcmesListResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/2/27.
 */

public class VideosRequest extends AcmesRequest {

    public static class Response extends AcmesListResponse<AcmesListResponse.Data<List<BVideo>>> {

    }


    @Override
    public Observable<Response> callAPI(AcmesAPI api) {
        return api.videos(this);
    }
}
