package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.ServerAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginRequest extends SwordFishRequest {

    public String name;
    public String password;

    public LoginRequest(String userName, String userPassword) {
        name = userName;
        password = userPassword;
    }

    public LoginRequest(String userName) {
        name = userName;
    }

    @Override
    public Observable callAPI(ServerAPI api) {
        return api.login(this);
    }
}
