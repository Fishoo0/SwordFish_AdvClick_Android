package com.acmes.acmes;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.acmes.simpleandroid.mvc.SimpleActivity;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import butterknife.ButterKnife;

/**
 * Created by fishyu on 2018/1/2.
 */

public class AcmesActivity<T extends AcmesMode> extends SimpleActivity<T> {

    static final SwipeRefreshLayout EMPTY = new SwipeRefreshLayout(AcmesApplication.getInstance());

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private void initSwipeRefreshLayout() {
        if (mSwipeRefreshLayout == null) {
            View view = findViewById(R.id.swipe_refresh_layout);
            if (view instanceof SwipeRefreshLayout) {
                mSwipeRefreshLayout = (SwipeRefreshLayout) view;
            } else {
                Log.e(TAG, "Find no SwipeRefreshLayout !");
                mSwipeRefreshLayout = EMPTY;
            }
        }
    }

    /**
     * Getting swipe refresh layout
     *
     * @return
     */
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        initSwipeRefreshLayout();
        return mSwipeRefreshLayout;
    }

    @Override
    public void onRequestStart(SimpleRequest request) {
        super.onRequestStart(request);
        getSwipeRefreshLayout().setRefreshing(true);
    }

    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        super.onFailure(request, exception);
        Log.e(TAG, exception.getMessage());
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();

        getSwipeRefreshLayout().setRefreshing(false);
    }

    @Override
    protected T createModel() {
        return (T) new AcmesMode();
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);

        getSwipeRefreshLayout().setRefreshing(false);
    }

}

