package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/3.
 */

public class CheckYoumengRequest extends AcmesRequest {

    public String id;
    public String youmeng_baidu;
    public String youmeng_google;
    public String youmeng_sougou;
    public String youmeng_taobao;

    public CheckYoumengRequest(String userId, String baidu, String google, String sougou, String taobao) {
        this.id = userId;
        this.youmeng_baidu = baidu;
        this.youmeng_google = google;
        this.youmeng_sougou = sougou;
        this.youmeng_taobao = taobao;
    }

    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.check_youmeng(this);
    }

}
