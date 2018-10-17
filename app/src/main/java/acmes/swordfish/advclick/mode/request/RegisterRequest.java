package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.AdvClickAPI;

import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class RegisterRequest extends AcmesRequest {

    public String user_name;
    public String user_password;


    public RegisterRequest(String userName, String userPassword) {
        user_name = userName;
        user_password = userPassword;
    }


    @Override
    public Observable callAPI(AdvClickAPI api) {
        return api.register(this);
    }
}
