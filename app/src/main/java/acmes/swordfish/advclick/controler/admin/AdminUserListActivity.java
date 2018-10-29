package acmes.swordfish.advclick.controler.admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class AdminUserListActivity extends AdvClickActivity implements View.OnClickListener {


    @BindView(R.id.listview)
    ListView mListView;

    Adapter mListAdapter;


    @Override
    protected void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_main_activity);

        mListAdapter = new Adapter();
        mListView.setAdapter(mListAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                final BUser user = (BUser) mListAdapter.getItem(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(AdminUserListActivity.this);
                builder.setItems(R.array.admin_operation_list, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                AdminUpdateProfileActivity.jumpToThis(AdminUserListActivity.this, user);
                                break;
                            case 1:
                                AdminUpdateMoneyActivity.jumpToThis(AdminUserListActivity.this, user);
                                break;
                            case 2:
                                AdminUserLogListActivity.jumpToThis(AdminUserListActivity.this, user);
                                break;
                        }
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();

            }
        });

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
            if (mData == null) {
                return null;
            }
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

            @BindView(R.id.name_textview)
            TextView mName;

            @BindView(R.id.name_password)
            TextView mPassword;

            @BindView(R.id.im_qq)
            TextView mQQ;

            @BindView(R.id.telephone)
            TextView mTelephone;

            @BindView(R.id.alipay)
            TextView mAlipay;

            @BindView(R.id.alipay_name)
            TextView mAlipayName;

            @BindView(R.id.prime)
            TextView mPrime;

            @BindView(R.id.youmeng_checked)
            TextView mYoumengChecked;

            @BindView(R.id.earn_count)
            TextView mEarn;

            @BindView(R.id.withdraw)
            TextView mWithDraw;

            public ItemView(@NonNull Context context) {
                super(context);

                LayoutInflater.from(context).inflate(R.layout.admin_user_list_item, this);
                ButterKnife.bind(this, this);
            }

            public void updateView(BUser data) {
                mName.setText("用户名：" + data.mUserName);
                mPassword.setText("密码：" + data.mUserPassword);
                mQQ.setText("QQ：" + data.mQQ);
                mTelephone.setText("电话：" + data.mTelephone);
                mAlipay.setText("支付宝：" + data.mAlipay);
                mAlipayName.setText("支付宝名字：" + data.mAlipayName);

                mPrime.setText("会员类型：" + data.getPrimeString() + "  " +
                        "更新日期：" + data.mPrimeOpenTime + "  " +
                        "终止日期：" + data.mPrimeEndTime
                );

                mYoumengChecked.setText("友盟状态：" + (data.isYoumengChecked() ? " 已绑定" : "未绑定"));

                mEarn.setText("余额：" + "点击查看更多具体信息");

                mWithDraw.setText("提现：" + "点击查看更多具体信息");

            }
        }


    }


}