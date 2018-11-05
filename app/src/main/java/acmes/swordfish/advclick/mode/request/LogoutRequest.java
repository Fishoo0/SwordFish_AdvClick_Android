package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.ServerAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/3.
 */

public class LogoutRequest extends SwordFishRequest {

    public String id;

    public LogoutRequest(String user_id) {
        id = user_id;
    }

    @Override
    public Observable callAPI(ServerAPI api) {
        return api.logout(this);
    }

}
