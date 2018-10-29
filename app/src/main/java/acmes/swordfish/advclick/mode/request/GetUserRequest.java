package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class GetUserRequest extends AcmesRequest {

    public String id;

    public GetUserRequest(String userId) {
        id = userId;
    }

    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.get_user(this);
    }
}
