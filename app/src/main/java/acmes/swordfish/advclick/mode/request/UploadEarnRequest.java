package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import acmes.swordfish.advclick.mode.bean.BSearch;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class UploadEarnRequest extends AcmesRequest {

    public String user_id;
    public float value;

    public BSearch mSearch;

    public UploadEarnRequest(String userId, BSearch search) {
        user_id = userId;
        mSearch = search;
        this.value = search.mEarn - search.mEarnUploaded;
    }


    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.upload_earn(this);
    }
}
