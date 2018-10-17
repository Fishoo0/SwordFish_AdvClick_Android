package com.acmes.simpleandroid.mvc;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import butterknife.ButterKnife;

/**
 * Created by fishyu on 2017/8/23.
 */

public abstract class SimpleFragment<T extends SimpleModel> extends Fragment implements ISimpleModeCallback {

    protected final String TAG = getClass().getSimpleName();

    private Handler mHandler = new Handler();
    private T mModel;
    private SimpleViewControllerHelper mViewControllerHelper = new SimpleViewControllerHelper();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.v(TAG, "onAttach -> " + context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "onActivityCreated");
        ButterKnife.bind(this, getView());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.v(TAG, "onStart");
        mViewControllerHelper.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.v(TAG, "onResume");
        mViewControllerHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.v(TAG, "onPause");
        mViewControllerHelper.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.v(TAG, "onStop");
        mViewControllerHelper.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.v(TAG, "onDestroyView");
        mViewControllerHelper.onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "onDestroy");
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
