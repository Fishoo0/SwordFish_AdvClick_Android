package com.acmes.simpleandroid.mvc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import butterknife.ButterKnife;


/**
 * Created by fishyu on 2017/8/23.
 */
public abstract class SimpleActivity<T extends SimpleModel> extends AppCompatActivity implements ISimpleModeCallback {

    protected final String TAG = getClass().getSimpleName();
    protected final boolean DEBUG = SimpleUtils.DEBUG;

    private Handler mHandler = new Handler();
    private T mModel;

    private SimpleViewControllerHelper mViewControllerHelper = new SimpleViewControllerHelper();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.v(TAG, "onNewIntent");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
        mViewControllerHelper.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
        mViewControllerHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
        mViewControllerHelper.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
        mViewControllerHelper.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
        mViewControllerHelper.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        if (mModel != null) {
            mModel.removeSimpleCallback(this);
            mModel.onDestroy();
            mModel = null;
        }

    }

    @Override
    public void onRequestStart(SimpleRequest request) {
        Log.v(TAG, "onRequestStart request -> " + request);
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        Log.v(TAG, "onResponse request -> " + request + " response -> " + response);
    }

    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        Log.v(TAG, "onFailure request -> " + request + " exception -> " + exception);
    }

    /**
     * Getting handler attached to this Controller
     *
     * @return
     */
    public Handler getHandler() {
        return mHandler;
    }

    /**
     * Creating instance of Model
     *
     * @return
     */
    protected abstract T createModel();

    /**
     * Getting instance of Model
     *
     * @return
     */
    public T getModel() {
        if (mModel == null) {
            mModel = createModel();
            if (mModel != null) {
                mModel.addSimpleCallback(this);
            }
        }
        return mModel;
    }

    protected SimpleViewControllerHelper getViewControllerHelper() {
        return mViewControllerHelper;
    }

}
