package com.acmes.acmes.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by fishyu on 2018/2/9.
 */

public class AcmesSwipeRefreshLayout extends SwipeRefreshLayout {

    public AcmesSwipeRefreshLayout(Context context) {
        super(context);
    }

    public AcmesSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void setRefreshing(boolean refreshing) {
        super.setRefreshing(refreshing);
    }

}
