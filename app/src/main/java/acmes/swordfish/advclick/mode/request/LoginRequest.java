package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.AdvClickAPI;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginRequest extends AcmesRequest {

    public String user_name;
    public String user_password;


    public LoginRequest(String userName, String userPassword) {
        user_name = userName;
        user_password = userPassword;
    }

    public LoginRequest(String userName) {
        user_name = userName;
    }


    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.login(this);
    }
}
