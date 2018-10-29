package acmes.swordfish.advclick.controler.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import java.util.List;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.login.SharedPrefAccountManager;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.AdminUserListRequest;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 */
public class AdminUserLogListActivity extends AdvClickActivity implements View.OnClickListener {


    @BindView(R.id.listview)
    ListView mListView;

    Adapter mListAdapter;


    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    private BUser mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_user_log_activity);


        mUser = (BUser) getIntent().getSerializableExtra("data");

        mListAdapter = new Adapter();
        mListView.setAdapter(mListAdapter);
    }


    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);

        if (request instanceof AdminUserListRequest) {
            if (response.getData() instanceof List) {

                //update view
                mListAdapter.mData = (List) response.getData();
                mListAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void onClick(View v) {

    }


    @Override
    public void onRefresh() {
        super.onRefresh();

        // start request
        getModel().performRequest(new AdminUserListRequest(SharedPrefAccountManager.getInstance().getCurrentUser().mUserId));
    }

    static class Adapter extends BaseAdapter {

        private List mData;

        public Adapter() {

        }

        @Override
        public int getCount() {
            return mData == null ? 0 : mData.size();
        }

        @Override
        public Object getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ItemView view;
            if (convertView instanceof ItemView) {
                view = (ItemView) convertView;
            } else {
                view = new ItemView(parent.getContext());
            }
            view.updateView((BUser) getItem(position));
            return view;
        }


        static class ItemView extends FrameLayout {

            @BindView(R.id.operation)
            TextView mOperation;

            @BindView(R.id.device)
            TextView mDevice;

            @BindView(R.id.network)
            TextView mNetwork;

            @BindView(R.id.location)
            TextView mLocation;

            @BindView(R.id.time)
            TextView mTime;


            public ItemView(@NonNull Context context) {
                super(context);

                LayoutInflater.from(context).inflate(R.layout.admin_user_log_list_item, this);
                ButterKnife.bind(this, this);
            }

            public void updateView(BUser data) {
                mOperation.setText("操作：" + data.mUserName);
                mDevice.setText("设备：" + data.mUserPassword);
                mNetwork.setText("网络：" + data.mQQ);
                mLocation.setText("地址："+data.mPrimeOpenTime);
                mTime.setText("时间：" + data.mTelephone);
            }
        }

    }


    public static final void jumpToThis(Context context, BUser data) {
        Intent intent = new Intent(context, AdminUserLogListActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }


}