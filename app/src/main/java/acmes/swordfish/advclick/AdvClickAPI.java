package acmes.swordfish.advclick;

import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.LoginRequest;
import acmes.swordfish.advclick.mode.request.LogoutRequest;
import acmes.swordfish.advclick.mode.request.RegisterRequest;
import acmes.swordfish.advclick.mode.response.AcmesResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fishyu on 2018/1/2.
 */

public interface AdvClickAPI {

    // emulator use
    String BASE_URL = "http://10.0.2.2:5000";

    @POST("auth/register")
    Observable<AcmesResponse<BUser>> register(@Body RegisterRequest loginRequest);

    @POST("auth/login")
    Observable<AcmesResponse<BUser>> login(@Body LoginRequest loginRequest);

    @POST("auth/logout")
    Observable<AcmesResponse> logout(@Body LogoutRequest request);


}
