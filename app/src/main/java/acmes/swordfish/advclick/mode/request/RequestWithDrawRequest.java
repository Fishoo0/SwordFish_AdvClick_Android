package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class RequestWithDrawRequest extends AcmesRequest {

    public String user_id;
    public float value;

    public RequestWithDrawRequest(String userId, float value) {
        this.user_id = userId;
        this.value = value;
    }

    public RequestWithDrawRequest(String userId) {
        this(userId, 0);
    }

    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.request_earn(this);
    }
}
