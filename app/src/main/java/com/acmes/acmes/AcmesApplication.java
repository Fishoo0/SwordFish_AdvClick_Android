package com.acmes.acmes;

import com.acmes.simpleandroid.imp.Square.SquareNetwork;
import com.acmes.simpleandroid.mvc.SimpleApplication;
import com.squareup.picasso.Picasso;

import hugo.weaving.DebugLog;
import retrofit2.Retrofit;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AcmesApplication extends SimpleApplication {

    public static AcmesApplication getInstance() {
        return (AcmesApplication) SimpleApplication.getInstance();
    }

    @DebugLog
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        mSquareNetwork = new SquareNetwork(this);
        updateProgress(20);
        while (getInitializeProgress() < 100) {
            try {
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateProgress(getInitializeProgress() + 1);
        }
    }

    protected SquareNetwork mSquareNetwork;

    public static Picasso getPicasso() {
        return getInstance().mSquareNetwork.getPicasso();
    }

    public Retrofit buildRetrofit(String url) {
        return mSquareNetwork.buildRetrofit(url);
    }

}
