package acmes.swordfish.advclick;

import com.acmes.simpleandroid.imp.Square.RetrofitSimpleModel;

import acmes.swordfish.advclick.mode.ServerAPI;
import retrofit2.Retrofit;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SwordFishMode extends RetrofitSimpleModel<ServerAPI> {

    static Retrofit sRetrofit = SwordFishApplication.getInstance().buildRetrofit(ServerAPI.BASE_URL);

    public SwordFishMode() {
        super(sRetrofit, ServerAPI.class);
    }
}
