package com.acmes.simpleandroid.mvc.model;

/**
 * Created by fishyu on 2017/8/23.
 */

public abstract class SimpleRequest<Response extends SimpleResponse> {

    public boolean isAppendLastResponse() {
        if (!mRefresh && mLastResponse != null) {
            return true;
        }
        return false;
    }


    /**
     * Updating response
     *
     * @param response
     */
    public void updateResponse(Response response) {
        if (isAppendLastResponse() && mLastResponse != null) {
            response.onAppendLastResponse(mLastResponse);
        }
        mLastResponse = response;
    }

    private transient Response mLastResponse;

    public Response getLastResponse() {
        return mLastResponse;
    }

    private transient boolean mRefresh = true;

    public void refresh() {
        mRefresh = true;
    }

    public void loadMore() {
        mRefresh = false;
    }

    private transient boolean mCanceled = false;

    public void cancel() {
        mCanceled = true;
    }

    public boolean isCanceled() {
        return mCanceled;
    }

    public abstract void buildParams(Object someNetEngineMaybe);


    /**
     * Getting tag for this request.
     *
     * @return
     */
    protected Object getTag() {
        return this;
    }

}
