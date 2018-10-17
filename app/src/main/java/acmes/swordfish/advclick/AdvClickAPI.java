package acmes.swordfish.advclick;

import acmes.swordfish.advclick.mode.request.CollectionRequest;
import acmes.swordfish.advclick.mode.request.SearchJavRequest;
import acmes.swordfish.advclick.mode.request.SearchRequest;
import acmes.swordfish.advclick.mode.request.VideoRequest;
import acmes.swordfish.advclick.mode.request.VideosRequest;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.CategoriesRequest;
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

    String BASE_URL = "http://45.77.179.207:5000";

    @POST("register")
    Observable<AcmesResponse<BUser>> register(@Body RegisterRequest loginRequest);

    @POST("login")
    Observable<AcmesResponse<BUser>> login(@Body LoginRequest loginRequest);

    @POST("logout")
    Observable<AcmesResponse> logout(@Body LogoutRequest request);

    @POST("categories")
    Observable<CategoriesRequest.Response> categories(@Body CategoriesRequest request);

    @POST("collections")
    Observable<CollectionRequest.Response> collections(@Body CollectionRequest request);

    @POST("videos")
    Observable<VideosRequest.Response> videos(@Body VideosRequest request);

    @POST("search")
    Observable<SearchRequest.Response> search(@Body SearchRequest request);

    @POST("search_jav")
    Observable<SearchJavRequest.Response> search_jav(@Body SearchJavRequest request);

    @POST("video")
    Observable<VideoRequest.Response> video(@Body VideoRequest request);

}
