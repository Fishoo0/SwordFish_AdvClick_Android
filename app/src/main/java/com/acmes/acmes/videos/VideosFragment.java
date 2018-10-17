package com.acmes.acmes.videos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acmes.acmes.AcmesFragment;
import com.acmes.acmes.R;
import com.acmes.acmes.mode.request.VideosRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import butterknife.BindView;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;

/**
 * Created by fishyu on 2018/2/26.
 */

public class VideosFragment extends AcmesFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recycle_view)
    RecyclerView mRecycleView;

    VideoListAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.videos_fragment_layout, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRecycleView.setItemAnimator(new SlideInLeftAnimator());

        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new VideoListAdapter();
        mRecycleView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        getSwipeRefreshLayout().setOnRefreshListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdapter.getItemCount() <= 0) {
            onRefresh();
        }
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (response instanceof VideosRequest.Response) {
            mAdapter.setData((VideosRequest.Response) response);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onRefresh() {
        getModel().performRequest(new VideosRequest());
    }
}
