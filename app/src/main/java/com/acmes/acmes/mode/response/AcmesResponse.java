package com.acmes.acmes.mode.response;

import com.acmes.simpleandroid.imp.Square.RetrofitSimpleResponse;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AcmesResponse<T> extends RetrofitSimpleResponse {

    public int code;
    public String message;
    public T data;


    public boolean isSuccess() {
        return code == 0;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }


    public boolean isEmpty() {
        return getData() == null;
    }

}
