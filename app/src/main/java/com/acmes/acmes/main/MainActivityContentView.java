package com.acmes.acmes.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.acmes.acmes.R;
import com.acmes.simpleandroid.mvc.ISimpleViewController;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import butterknife.ButterKnife;

/**
 * Created by fishyu on 2018/2/27.
 */

public class MainActivityContentView extends FrameLayout implements ISimpleViewController<MainActivity> {

    static final String TAG = MainActivityContentView.class.getSimpleName();


    public MainActivityContentView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MainActivityContentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MainActivityContentView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.main_activity_content, this);
        ButterKnife.bind(this);
    }

    @Override
    public void registered() {
        Log.v(TAG, "registered");
    }

    @Override
    public void onCreate(Bundle savedInstance) {
        Log.v(TAG, "onCreate");
    }

    @Override
    public void onStart() {
        Log.v(TAG, "onStart");
    }

    @Override
    public void onResume() {
        Log.v(TAG, "onResume");
    }

    @Override
    public void onPause() {
        Log.v(TAG, "onPause");
    }

    @Override
    public void onStop() {
        Log.v(TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy");
    }

    @Override
    public void unregistered() {
        Log.v(TAG, "unregistered");
    }

    @Override
    public MainActivity getMasterContext() {
        return (MainActivity) getContext();
    }

    @Override
    public void onRequestStart(SimpleRequest request) {
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
    }

    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
    }

}
