package acmes.swordfish.advclick.mode.request;

import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/3.
 */

public class UpdateProfileRequest extends AcmesRequest {

    public String id;
    public String password;
    public String im_qq;
    public String telephone;

    public String alipay;
    public String alipay_name;
    public int prime_level;
    public long prime_period;
    public int youmeng_checked;


    public UpdateProfileRequest(String userId, String password, String im_qq, String telephone) {
        this.id = userId;
        this.password = password;
        this.im_qq = im_qq;
        this.telephone = telephone;
    }


    public UpdateProfileRequest(String userId, String password, String im_qq, String telephone, String alipay, String alipay_name, int prime_level, String prime_period, boolean youmeng_checked) {
        this.id = userId;
        this.password = password;
        this.im_qq = im_qq;
        this.telephone = telephone;

        this.alipay = alipay;
        this.alipay_name = alipay_name;
        this.prime_level = prime_level;
        this.prime_period = Utils.parseLong(prime_period, -1);
        this.youmeng_checked = youmeng_checked ? 1 : 0;
    }

    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.update_profile(this);
    }

}
