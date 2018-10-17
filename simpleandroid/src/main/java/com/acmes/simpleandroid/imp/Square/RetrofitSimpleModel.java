package com.acmes.simpleandroid.imp.Square;

import android.util.Log;

import com.acmes.simpleandroid.mvc.ISimpleModeCallback;
import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by fishyu on 2017/8/23.
 */

public class RetrofitSimpleModel<API> extends SimpleModel {

    protected final String TAG = getClass().getSimpleName();

    protected API mAPI;

    public API getAPI() {
        return mAPI;
    }

    public RetrofitSimpleModel(Retrofit retrofit, Class<API> clz) {
        super();
        mAPI = retrofit.create(clz);
    }

    @Override
    protected void onCancelRequest(Object request, Object callable) {
        Disposable disposable = (Disposable) callable;
        disposable.dispose();
    }

    @Override
    public void performRequest(SimpleRequest request, ISimpleModeCallback callback) {
        if (!(request instanceof RetrofitSimpleRequest)) {
            throw new IllegalArgumentException("RetrofitSimpleModel only accepts RetrofitSimpleRequest");
        }
        performRequestRetrofit(request, callback, ((RetrofitSimpleRequest) request).callAPI(getAPI()));
    }

    /**
     * @param request
     * @param callback
     * @param observable
     * @param <T>
     * @return
     */
    protected <T extends SimpleResponse> RetrofitSimpleModel performRequestRetrofit(final SimpleRequest request, final ISimpleModeCallback callback, Observable<T> observable) {
        Log.v(TAG, "performRequest");
        Disposable disposable = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        if (t.isSuccess()) {
                            if (request.getLastResponse() != null) {
                                t.onAppendLastResponse(request.getLastResponse());
                            }
                        }
                        RetrofitSimpleModel.this.onResponse(request, t, callback);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        RetrofitSimpleModel.this.onFailure(request, throwable, callback);
                    }
                });

        RetrofitSimpleModel.this.onRequestStart(request, disposable, callback);
        return this;
    }

}
