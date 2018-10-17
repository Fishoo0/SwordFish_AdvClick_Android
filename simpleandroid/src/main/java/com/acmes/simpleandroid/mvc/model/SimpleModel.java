package com.acmes.simpleandroid.mvc.model;

import com.acmes.simpleandroid.mvc.ISimpleModeCallback;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fishyu on 2017/8/23.
 */

public abstract class SimpleModel implements ISimpleModeCallback {

    protected final String TAG = getClass().getSimpleName();

    protected List<ISimpleModeCallback> mSimpleCallbacks = new ArrayList<>();

    public SimpleModel() {
    }

    /**
     * Setting simple callback
     *
     * @param callback
     */
    public void addSimpleCallback(ISimpleModeCallback callback) {
        if (callback != null) {
            if (!mSimpleCallbacks.contains(callback)) {
                mSimpleCallbacks.add(callback);
            }
        }
    }


    /**
     * Removing simple callback
     *
     * @param callback
     */
    public void removeSimpleCallback(ISimpleModeCallback callback) {
        if (callback != null) {
            mSimpleCallbacks.remove(callback);
        }
    }


    protected Map<SimpleRequest, Object> mRequests = new LinkedHashMap<>();

    protected Map<SimpleRequest, Object> getRequestsQueen() {
        return mRequests;
    }


    protected abstract void onCancelRequest(Object request, Object callable);

    /**
     * Destroy all requests
     */
    public void onDestroy() {
        Iterator<SimpleRequest> iterator = mRequests.keySet().iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            onCancelRequest(key, mRequests.get(key));
        }
        mRequests.clear();
        mSimpleCallbacks.clear();
    }


    /**
     * Cancel requests
     *
     * @param request
     */
    public void cancelRequest(Object request) {
    }


    /**
     * Perform request
     *
     * @param request
     * @param callback
     */
    public abstract void performRequest(final SimpleRequest request, final ISimpleModeCallback callback);


    /**
     * Perform request
     *
     * @param request
     */
    public void performRequest(final SimpleRequest request) {
        performRequest(request, this);
    }


    /**
     * Call this method if u want to update request status onRequestStart
     *
     * @param request
     * @param cancelable Used to cancel request
     * @param callback
     */
    protected void onRequestStart(SimpleRequest request, Object cancelable, ISimpleModeCallback callback) {
        if (getRequestsQueen().containsKey(request)) {
            onCancelRequest(request, getRequestsQueen().get(request));
        }
        mRequests.put(request, cancelable);
        callback.onRequestStart(request);
    }

    /**
     * Call this method if u want to update request status onResponse
     *
     * @param request
     * @param response
     * @param callback
     */
    protected void onResponse(SimpleRequest request, SimpleResponse response, ISimpleModeCallback callback) {
        mRequests.remove(request);
        callback.onResponse(request, response);
    }

    /**
     * Call this method if u want to update request status onFailure
     *
     * @param request
     * @param exception
     * @param callback
     */
    protected void onFailure(SimpleRequest request, Throwable exception, ISimpleModeCallback callback) {
        mRequests.remove(request);
        callback.onFailure(request, exception);
    }

    @Override
    public void onRequestStart(SimpleRequest request) {
        for (ISimpleModeCallback callback : mSimpleCallbacks) {
            callback.onRequestStart(request);
        }
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        for (ISimpleModeCallback callback : mSimpleCallbacks) {
            callback.onResponse(request, response);
        }
    }

    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        for (ISimpleModeCallback callback : mSimpleCallbacks) {
            callback.onFailure(request, exception);
        }
    }

}
