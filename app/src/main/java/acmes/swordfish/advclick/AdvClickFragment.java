package acmes.swordfish.advclick;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.acmes.simpleandroid.mvc.SimpleApplication;
import com.acmes.simpleandroid.mvc.SimpleFragment;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2018/2/26.
 */

public class AdvClickFragment<T extends AdvClickMode> extends SimpleFragment<T> implements SwipeRefreshLayout.OnRefreshListener {

    static final SwipeRefreshLayout EMPTY = new SwipeRefreshLayout(AdvClickApplication.getInstance());

    private SwipeRefreshLayout mSwipeRefreshLayout = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initSwipeRefreshLayout();

    }

    private void initSwipeRefreshLayout() {
        if (mSwipeRefreshLayout == null) {
            View view = null;
            if (getView() != null) {
                if (getView() instanceof SwipeRefreshLayout) {
                    view = getView();
                } else {
                    view = getView().findViewById(R.id.swipe_refresh_layout);
                }
            }

            if (view instanceof SwipeRefreshLayout) {
                mSwipeRefreshLayout = (SwipeRefreshLayout) view;
                mSwipeRefreshLayout.setOnRefreshListener(this);
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
        if (request.isShowProgressBar()) {
            getSwipeRefreshLayout().setRefreshing(true);
        }
    }

    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        super.onFailure(request, exception);
        Log.e(TAG, exception.getMessage());
        Toast.makeText(SimpleApplication.getInstance(), exception.getMessage(), Toast.LENGTH_SHORT).show();
        if (request.isShowProgressBar()) {
            getSwipeRefreshLayout().setRefreshing(false);
        }
    }

    @Override
    protected T createModel() {
        return (T) new AdvClickMode();
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (request.isShowProgressBar()) {
            getSwipeRefreshLayout().setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 1000);
    }
}
