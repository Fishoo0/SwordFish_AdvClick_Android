package acmes.swordfish.advclick.mode.request;

import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/3.
 */

public class AdminUpdateMoneyRequest extends AcmesRequest {

    public String user_id;

    public float earn_amount;
    public int with_draw_times_left;

    public float request_with_draw_amount;


    public AdminUpdateMoneyRequest(String userId, String earnAmount, String withdrawTimesLeft, String requestWithdrawAmount) {
        this.user_id = userId;
        this.earn_amount = Utils.parseFloat(earnAmount, -1);
        this.with_draw_times_left = Utils.parseInt(withdrawTimesLeft, -1);
        this.request_with_draw_amount = Utils.parseFloat(requestWithdrawAmount, -1);
    }


    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.admin_update_money(this);
    }

}
