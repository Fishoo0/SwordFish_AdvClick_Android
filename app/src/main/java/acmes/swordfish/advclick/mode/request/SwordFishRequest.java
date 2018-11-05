package acmes.swordfish.advclick.mode.request;

import com.acmes.simpleandroid.imp.Square.RetrofitSimpleRequest;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.SwordFishApplication;
import acmes.swordfish.advclick.mode.ServerAPI;
import acmes.swordfish.advclick.mode.response.SwordFishResponse;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SwordFishRequest<T extends SwordFishResponse> extends RetrofitSimpleRequest<T, ServerAPI> {


    public String location = Utils.getLocation(SwordFishApplication.getInstance());

    public String ip = Utils.getIpAddress(SwordFishApplication.getInstance());


    @Override
    public Observable<T> callAPI(ServerAPI api) {
        return null;
    }
}
