package acmes.swordfish.advclick.mode.request;

import com.acmes.simpleandroid.imp.Square.RetrofitSimpleRequest;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.AdvClickApplication;
import acmes.swordfish.advclick.mode.AdvClickAPI;
import acmes.swordfish.advclick.mode.response.AcmesResponse;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AcmesRequest<T extends AcmesResponse> extends RetrofitSimpleRequest<T, AdvClickAPI> {


    public String location = Utils.getLocation(AdvClickApplication.getInstance());

    public String ip = Utils.getIpAddress(AdvClickApplication.getInstance());


    @Override
    public Observable<T> callAPI(AdvClickAPI api) {
        return null;
    }
}
