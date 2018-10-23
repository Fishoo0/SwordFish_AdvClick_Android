package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginRequest extends AcmesRequest {

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
    public Observable callAPI(AdvClickAPI api) {
        return api.login(this);
    }
}
