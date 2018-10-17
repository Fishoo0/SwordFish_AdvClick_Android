package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.AdvClickAPI;
import acmes.swordfish.advclick.mode.bean.BVideo;
import acmes.swordfish.advclick.mode.response.AcmesResponse;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/2/27.
 */

public class VideoRequest extends AcmesRequest {

    public static final class Response extends AcmesResponse<BVideo> {

    }


    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.video(this);
    }
}
