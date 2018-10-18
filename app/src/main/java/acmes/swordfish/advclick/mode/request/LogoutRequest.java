package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.AdvClickAPI;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/3.
 */

public class LogoutRequest extends AcmesRequest {

    public String name;
    public String password;

    public LogoutRequest(String userName) {
        name = userName;
    }

    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.logout(this);
    }

}
