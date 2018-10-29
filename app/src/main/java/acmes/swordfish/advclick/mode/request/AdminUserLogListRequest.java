package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AdminUserLogListRequest extends AcmesRequest {

    public String user_id;

    public AdminUserLogListRequest(String userId) {
        this.user_id = userId;
    }

    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.admin_user_log_list(this);
    }
}
