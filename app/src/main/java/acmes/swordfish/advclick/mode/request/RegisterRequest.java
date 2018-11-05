package acmes.swordfish.advclick.mode.request;

import acmes.swordfish.advclick.mode.ServerAPI;
import io.reactivex.Observable;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 * id integer primary key autoincrement,
 * time text not null,
 * <p>
 * name text not null,
 * baidu text not null,
 * token text,
 * <p>
 * cover text,
 * sex text,
 * gender integer,
 * birth text,
 * age text,
 * location text,
 * about text,
 * <p>
 * im_qq text not null,
 * alipay text not null,
 * alipay_name text,
 * <p>
 * prime_level integer default 1,
 * prime_open_time integer default 0,
 * prime_level_duration default -1,
 * <p>
 * youmeng text
 */

public class RegisterRequest extends SwordFishRequest {

    public String name;
    public String password;

    public String im_qq;
    public String alipay;
    public String alipay_name;


    public RegisterRequest(String userName, String userPassword) {
        name = userName;
        password = userPassword;
    }


    public RegisterRequest(String name, String password, String qq, String alipay, String alipay_name) {
        this.name = name;
        this.password = password;
        this.im_qq = qq;
        this.alipay = alipay;
        this.alipay_name = alipay_name;
    }


    @Override
    public Observable callAPI(ServerAPI api) {
        return api.register(this);
    }
}
