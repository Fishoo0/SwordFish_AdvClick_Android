package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.AdvClickAPI;
import acmes.swordfish.advclick.mode.bean.BCollection;
import acmes.swordfish.advclick.mode.response.AcmesResponse;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/2/27.
 */

public class CollectionRequest extends AcmesRequest {

    public static final class Response extends AcmesResponse<List<BCollection>> {

    }

    @Override
    public Observable<Response> callAPI(AdvClickAPI api) {
        return api.collections(this);
    }
}
