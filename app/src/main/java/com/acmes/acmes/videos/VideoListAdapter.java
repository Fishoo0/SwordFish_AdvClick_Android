package com.acmes.acmes.videos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acmes.acmes.AcmesApplication;
import com.acmes.acmes.R;
import com.acmes.acmes.mode.bean.BVideo;
import com.acmes.acmes.mode.request.VideosRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fishyu on 2018/2/1.
 */

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.ViewHolder> {

    public VideosRequest.Response mData;

    public void setData(VideosRequest.Response data) {
        mData = data;
    }

    /**
     * Dealing with view
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_imageview)
        ImageView mContentImageView;

        @BindView(R.id.title)
        TextView mTitle;

        @BindView(R.id.count)
        TextView mCount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public ViewHolder(ViewGroup parent) {
            this(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_activity_content_list_item, parent, false));
        }

        /**
         * Updating view
         *
         * @param data
         */
        public void updateView(BVideo data) {

            AcmesApplication.getPicasso()
                    .load(data.preview_url)
                    .into(mContentImageView);

            mTitle.setText(data.title);
            mCount.setText(data.likes);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updateView(mData.getData().getList().get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null || mData.isEmpty() ? 0 : mData.getData().getListSize();
    }
}
