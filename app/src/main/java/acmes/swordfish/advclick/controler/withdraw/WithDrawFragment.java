package acmes.swordfish.advclick.controler.withdraw;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.login.DispatcherActivity;
import acmes.swordfish.advclick.controler.login.SharedPrefAccountManager;
import acmes.swordfish.advclick.controler.main.MainContentFragment;
import acmes.swordfish.advclick.mode.bean.BEarn;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.GetEarnRequest;
import acmes.swordfish.advclick.mode.request.RequestWithDrawRequest;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/10/17.
 */

public class WithDrawFragment extends MainContentFragment implements View.OnClickListener {

    @BindView(R.id.spinner)
    Spinner mSpinner;

    @BindView(R.id.alipay)
    TextView mPayAccount;

    @BindView(R.id.alipay_name)
    TextView mPayUserName;

    @BindView(R.id.earn_count)
    TextView mEarnCount;

    @BindView(R.id.with_draw_times)
    TextView mWithDrawTimes;

    @BindView(R.id.with_draw_response_textview)
    TextView mResultMonitor;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.withdraw_fragment, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BUser user = SharedPrefAccountManager.getInstance().getCurrentUser();
        if (user != null) {
            mPayAccount.setText(user.mAlipay);
            mPayUserName.setText(user.mAlipayName);
        } else {
            DispatcherActivity.jumpToThis(getContext(), DispatcherActivity.CMD_LOGOUT);
        }

        // 查询结果
        onClick(getView().findViewById(R.id.with_draw_response_button));
    }


    @Override
    public void onRequestStart(SimpleRequest request) {
        super.onRequestStart(request);
    }

    @Override
    public void onRefresh() {
        GetEarnRequest request = new GetEarnRequest(SharedPrefAccountManager.getInstance().getCurrentUser());
        getModel().performRequest(request);
    }


    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (response.getData() instanceof BEarn) {
            BEarn data = (BEarn) response.getData();
            updateView(data);
        }

        Utils.showToast(response.getMessage());
    }

    @OnClick({R.id.with_draw_request_button, R.id.with_draw_response_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.with_draw_request_button:
                RequestWithDrawRequest request =
                        new RequestWithDrawRequest(
                                SharedPrefAccountManager.getInstance().getCurrentUser().mUserId);
                getModel().performRequest(request);
                break;
            case R.id.with_draw_response_button:
                onRefresh();
                break;
        }
    }


    @SuppressLint("StringFormatMatches")
    private void updateView(BEarn data) {
        mEarnCount.setText(String.format(getString(R.string.format_yuan), data.mEarnAmount));
        mWithDrawTimes.setText(String.format(getString(R.string.format_withdraw_times_left), data.mWithDrawTimesLeft));

        // generate result
        StringBuilder stringBuilder = new StringBuilder();
        if (data.mLastManagerResponseAmout < 0 || TextUtils.isEmpty(data.mLastRequestTime)) {
            stringBuilder.append("从未发起过提现");
        } else {
            stringBuilder.append(data.mLastRequestTime);
            stringBuilder.append(" (");
            if (data.mLastRequestAmount == 0) {
                stringBuilder.append("已申请，已于 ");
                stringBuilder.append(data.mLastManagerResponseTime);
                stringBuilder.append(" 处理");
            } else {
                stringBuilder.append("已申请，未处理");
            }
            stringBuilder.append(")");
        }
        mResultMonitor.setText(stringBuilder.toString());
    }

}
