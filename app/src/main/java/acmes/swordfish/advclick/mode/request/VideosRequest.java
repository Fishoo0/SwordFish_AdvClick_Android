package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.AdvClickAPI;
import acmes.swordfish.advclick.mode.bean.BVideo;
import acmes.swordfish.advclick.mode.response.AcmesListResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/2/27.
 */

public class VideosRequest extends AcmesRequest {

    public static class Response extends AcmesListResponse<AcmesListResponse.Data<List<BVideo>>> {

    }


    @Override
    public Observable<Response> callAPI(AdvClickAPI api) {
        return api.videos(this);
    }
}
