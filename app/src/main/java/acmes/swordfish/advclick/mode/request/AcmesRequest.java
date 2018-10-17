package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.AdvClickAPI;
import acmes.swordfish.advclick.mode.response.AcmesResponse;
import com.acmes.simpleandroid.imp.Square.RetrofitSimpleRequest;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AcmesRequest<T extends AcmesResponse> extends RetrofitSimpleRequest<T, AdvClickAPI> {

    @Override
    public Observable<T> callAPI(AdvClickAPI api) {
        return null;
    }
}
