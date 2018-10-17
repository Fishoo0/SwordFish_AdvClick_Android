package com.acmes.acmes.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.acmes.acmes.R;
import com.acmes.acmes.message.MessageActivity;
import com.acmes.acmes.settings.AboutActivity;
import com.acmes.acmes.settings.SettingActivity;
import com.acmes.acmes.videos.CategoriesActivity;
import com.acmes.acmes.videos.CollectionsActivity;
import com.acmes.acmes.videos.SearchActivity;
import com.acmes.simpleandroid.mvc.ISimpleViewController;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/2/28.
 */

public class MainNavigationView extends FrameLayout implements ISimpleViewController<MainActivity>, View.OnClickListener {

    @BindView(R.id.find_menu_layout)
    View mFindButtonContent;


    public MainNavigationView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public MainNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MainNavigationView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.main_navigation_view, this);
        ButterKnife.bind(this, this);
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

    @Override
    public void registered() {

    }

    @Override
    public void onCreate(Bundle savedInstance) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void unregistered() {

    }

    @Override
    public MainActivity getMasterContext() {
        return (MainActivity) getContext();
    }

    @OnClick({R.id.find, R.id.collections, R.id.categories, R.id.search, R.id.message, R.id.setting, R.id.about})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find:
                if (mFindButtonContent.getVisibility() == VISIBLE) {
                    mFindButtonContent.setVisibility(GONE);
                } else {
                    mFindButtonContent.setVisibility(VISIBLE);
                }
                break;

            case R.id.collections:
                getMasterContext().startActivity(new Intent(getMasterContext(), CollectionsActivity.class));
                break;
            case R.id.categories:
                getMasterContext().startActivity(new Intent(getMasterContext(), CategoriesActivity.class));
                break;
            case R.id.search:
                getMasterContext().startActivity(new Intent(getMasterContext(), SearchActivity.class));
                break;

            case R.id.message:
                getMasterContext().startActivity(new Intent(getMasterContext(), MessageActivity.class));
                break;

            case R.id.setting:
                getMasterContext().startActivity(new Intent(getMasterContext(), SettingActivity.class));
                break;

            case R.id.about:
                getMasterContext().startActivity(new Intent(getMasterContext(), AboutActivity.class));
                break;
        }
    }
}
