package com.acmes.simpleandroid.mvc.model;

/**
 * Created by fishyu on 2017/8/23.
 */

public abstract class SimpleResponse<T> {

    // do nothing ...
    public void onAppendLastResponse(SimpleResponse<T> old) {
        // do nothing
    }


    /**
     * Getting result from server
     *
     * @return
     */
    public abstract boolean isSuccess();


    /**
     * Getting message from server
     *
     * @return
     */
    public abstract String getMessage();


    /**
     * Getting data from server
     *
     * @return
     */
    public abstract T getData();
}
