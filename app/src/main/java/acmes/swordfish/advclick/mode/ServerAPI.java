package acmes.swordfish.advclick.mode;

import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.LoginRequest;
import acmes.swordfish.advclick.mode.request.LogoutRequest;
import acmes.swordfish.advclick.mode.request.RegisterRequest;
import acmes.swordfish.advclick.mode.response.SwordFishResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fishyu on 2018/1/2.
 */

public interface ServerAPI {

    // emulator use
    String BASE_URL = "http://106.15.196.127:5000";

    @POST("auth/register")
    Observable<SwordFishResponse<BUser>> register(@Body RegisterRequest loginRequest);

    @POST("auth/login")
    Observable<SwordFishResponse<BUser>> login(@Body LoginRequest loginRequest);

    @POST("auth/logout")
    Observable<SwordFishResponse> logout(@Body LogoutRequest request);


    String IMAGE_PATH = BASE_URL + "/image/get_image?name=";

    String IMAGE_WECHAT_CARD = IMAGE_PATH + "wechat_card.png";

}
