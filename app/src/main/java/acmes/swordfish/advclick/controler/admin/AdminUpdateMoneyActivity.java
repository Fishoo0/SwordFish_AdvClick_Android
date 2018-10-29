package acmes.swordfish.advclick.controler.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.mode.bean.BEarn;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.AdminOneKeyWithdrawRequest;
import acmes.swordfish.advclick.mode.request.AdminUpdateMoneyRequest;
import acmes.swordfish.advclick.mode.request.GetEarnRequest;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/10/17.
 */

public class AdminUpdateMoneyActivity extends AdvClickActivity implements View.OnClickListener {

    @BindView(R.id.earn_amoumt)
    EditText mEarnAmount;

    @BindView(R.id.withdraw_request)
    EditText mWithDrawAmount;

    @BindView(R.id.withdraw_times_left)
    EditText mWithDrawTimesLeft;

    @BindView(R.id.extra_info)
    TextView mExtraInfo;

    private BUser mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_update_money_activity);

        mUser = (BUser) getIntent().getSerializableExtra("data");

        onRefresh();
    }

    private void updateView(Object objData) {
        if (objData instanceof BEarn) {
            BEarn data = (BEarn) objData;

            mEarnAmount.setText("");
            mWithDrawAmount.setText("");
            mWithDrawTimesLeft.setText("");

            mEarnAmount.setHint(data.getEarnAmountString());
            mWithDrawAmount.setHint(data.getRequestWithDrawAmountString());
            mWithDrawTimesLeft.setHint(String.valueOf(data.mWithDrawTimesLeft));

            mExtraInfo.setText("" + data.mLastRequestTime);

        }
    }

    @OnClick({R.id.update_money_button, R.id.withdraw_request_onekey})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_money_button:
                AdminUpdateMoneyRequest request =
                        new AdminUpdateMoneyRequest(
                                mUser.mUserId,
                                mEarnAmount.getText().toString(),
                                mWithDrawTimesLeft.getText().toString(),
                                mWithDrawAmount.getText().toString()
                        );
                getModel().performRequest(request);
                break;

            case R.id.withdraw_request_onekey:
                AdminOneKeyWithdrawRequest onekeyRequest = new AdminOneKeyWithdrawRequest(mUser.mUserId);
                getModel().performRequest(onekeyRequest);
                break;
        }
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);

        if (response.isSuccess()) {
            updateView(response.getData());
        }


        Utils.showToast(response.getMessage());
    }


    @Override
    public void onRefresh() {
        super.onRefresh();

        GetEarnRequest request = new GetEarnRequest(mUser.mUserId);
        getModel().performRequest(request);
    }

    public static final void jumpToThis(Context context, BUser data) {
        Intent intent = new Intent(context, AdminUpdateMoneyActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }
}
