package acmes.swordfish.advclick;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.acmes.simpleandroid.mvc.SimpleActivity;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SwordFishActivity<T extends SwordFishMode> extends SimpleActivity<T> implements SwipeRefreshLayout.OnRefreshListener {

    static final SwipeRefreshLayout EMPTY = new SwipeRefreshLayout(SwordFishApplication.getInstance());

    private SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onContentViewCreated() {
        super.onContentViewCreated();
        initSwipeRefreshLayout();
    }

    private void initSwipeRefreshLayout() {
        if (mSwipeRefreshLayout == null) {
            View view = findViewById(R.id.swipe_refresh_layout);
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
        getSwipeRefreshLayout().setRefreshing(true);
    }

    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        super.onFailure(request, exception);
        if (exception == null || TextUtils.isEmpty(exception.getMessage())) {
            exception = new Exception("Unknown Error !");
        }
        Log.e(TAG, exception.getMessage());
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();

        getSwipeRefreshLayout().setRefreshing(false);
    }

    @Override
    protected T createModel() {
        return (T) new SwordFishMode();
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);

        getSwipeRefreshLayout().setRefreshing(false);
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

