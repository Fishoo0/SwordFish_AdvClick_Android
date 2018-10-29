package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/3.
 */

public class AdminOneKeyWithdrawRequest extends AcmesRequest {

    public String user_id;


    public AdminOneKeyWithdrawRequest(String userId) {
        this.user_id = userId;
    }

    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.admin_onekey_withdraw(this);
    }
}
