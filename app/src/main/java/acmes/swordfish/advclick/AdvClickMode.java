package acmes.swordfish.advclick;

import com.acmes.simpleandroid.imp.Square.RetrofitSimpleModel;

import acmes.swordfish.advclick.mode.AdvClickAPI;
import retrofit2.Retrofit;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AdvClickMode extends RetrofitSimpleModel<AdvClickAPI> {

    static Retrofit sRetrofit = AdvClickApplication.getInstance().buildRetrofit(AdvClickAPI.BASE_URL);

    public AdvClickMode() {
        super(sRetrofit, AdvClickAPI.class);
    }
}
