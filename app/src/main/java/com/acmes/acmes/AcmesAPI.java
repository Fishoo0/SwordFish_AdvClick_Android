package com.acmes.acmes;

import com.acmes.acmes.mode.request.CollectionRequest;
import com.acmes.acmes.mode.request.SearchJavRequest;
import com.acmes.acmes.mode.request.SearchRequest;
import com.acmes.acmes.mode.request.VideoRequest;
import com.acmes.acmes.mode.request.VideosRequest;
import com.acmes.acmes.mode.bean.BUser;
import com.acmes.acmes.mode.request.CategoriesRequest;
import com.acmes.acmes.mode.request.LoginRequest;
import com.acmes.acmes.mode.request.LogoutRequest;
import com.acmes.acmes.mode.request.RegisterRequest;
import com.acmes.acmes.mode.response.AcmesResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fishyu on 2018/1/2.
 */

public interface AcmesAPI {

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
