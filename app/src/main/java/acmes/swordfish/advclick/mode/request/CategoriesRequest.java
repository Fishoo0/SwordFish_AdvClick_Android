package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.AdvClickAPI;
import acmes.swordfish.advclick.mode.bean.BCategory;
import acmes.swordfish.advclick.mode.response.AcmesResponse;

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
    public Observable<Response> callAPI(AdvClickAPI api) {
        return api.categories(this);
    }
}
