package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AdminUserListRequest extends AcmesRequest {

    public String user_id;

    public AdminUserListRequest(String userId) {
        this.user_id = userId;
    }

    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.admin_user_list(this);
    }
}
