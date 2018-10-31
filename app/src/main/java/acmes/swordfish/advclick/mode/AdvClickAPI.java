package acmes.swordfish.advclick.mode;

import java.util.List;

import acmes.swordfish.advclick.mode.bean.BEarn;
import acmes.swordfish.advclick.mode.bean.BLog;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.AdminOneKeyWithdrawRequest;
import acmes.swordfish.advclick.mode.request.AdminUpdateMoneyRequest;
import acmes.swordfish.advclick.mode.request.AdminUserListRequest;
import acmes.swordfish.advclick.mode.request.AdminUserLogListRequest;
import acmes.swordfish.advclick.mode.request.CheckYoumengRequest;
import acmes.swordfish.advclick.mode.request.GetEarnRequest;
import acmes.swordfish.advclick.mode.request.GetUserRequest;
import acmes.swordfish.advclick.mode.request.LoginRequest;
import acmes.swordfish.advclick.mode.request.LogoutRequest;
import acmes.swordfish.advclick.mode.request.RegisterRequest;
import acmes.swordfish.advclick.mode.request.RequestWithDrawRequest;
import acmes.swordfish.advclick.mode.request.UpdateProfileRequest;
import acmes.swordfish.advclick.mode.request.UploadEarnRequest;
import acmes.swordfish.advclick.mode.response.AcmesResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fishyu on 2018/1/2.
 */

public interface AdvClickAPI {

    // emulator use
    String BASE_URL = "http://106.15.196.127:5000";

    @POST("auth/register")
    Observable<AcmesResponse<BUser>> register(@Body RegisterRequest loginRequest);

    @POST("auth/login")
    Observable<AcmesResponse<BUser>> login(@Body LoginRequest loginRequest);

    @POST("auth/logout")
    Observable<AcmesResponse> logout(@Body LogoutRequest request);

    @POST("auth/get_user")
    Observable<AcmesResponse<BUser>> get_user(@Body GetUserRequest request);

    @POST("auth/update_profile")
    Observable<AcmesResponse<BUser>> update_profile(@Body UpdateProfileRequest request);

    @POST("auth/check_youmeng")
    Observable<AcmesResponse<BUser>> check_youmeng(@Body CheckYoumengRequest request);

    @POST("click/get_earn")
    Observable<AcmesResponse<BEarn>> get_earn(@Body GetEarnRequest request);

    @POST("click/upload_earn")
    Observable<AcmesResponse<BEarn>> upload_earn(@Body UploadEarnRequest request);

    @POST("click/request_withdraw")
    Observable<AcmesResponse<BEarn>> request_earn(@Body RequestWithDrawRequest request);


    @POST("admin/get_users")
    Observable<AcmesResponse<List<BUser>>> admin_user_list(@Body AdminUserListRequest request);

    @POST("admin/update_money")
    Observable<AcmesResponse<BEarn>> admin_update_money(@Body AdminUpdateMoneyRequest request);

    @POST("admin/onekey_withdraw")
    Observable<AcmesResponse<BEarn>> admin_onekey_withdraw(@Body AdminOneKeyWithdrawRequest request);


    @POST("log/get_logs")
    Observable<AcmesResponse<List<BLog>>> admin_user_log_list(@Body AdminUserLogListRequest request);


    String IMAGE_PATH = BASE_URL + "/image/get_image?name=";

    String IMAGE_WECHAT_CARD = IMAGE_PATH + "wechat_card.png";

}
