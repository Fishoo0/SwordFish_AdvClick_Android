package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import acmes.swordfish.advclick.mode.bean.BUser;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class GetEarnRequest extends AcmesRequest {

    public String user_id;

    public GetEarnRequest(BUser user) {
        user_id = user.mUserId;
    }

    public GetEarnRequest(String userId) {
        this.user_id = userId;
    }

    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.get_earn(this);
    }
}
