package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class UploadEarnRequest extends AcmesRequest {

    public String user_id;
    public float value;

    public UploadEarnRequest(String userId, float value) {
        user_id = userId;
        this.value = value;
    }


    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.upload_earn(this);
    }
}
