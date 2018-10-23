package acmes.swordfish.advclick.controler.function;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.main.MainContentFragment;
import acmes.swordfish.advclick.mode.bean.BEarn;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.view.OnlineNumberTextView;
import acmes.swordfish.advclick.view.PrimeTrialTextView;
import butterknife.BindView;

/**
 * Created by fishyu on 2018/10/17.
 */

public class FunctionFragment extends MainContentFragment {


    RadioButton[] mRatios = new RadioButton[]{null, null, null, null};


    @BindView(R.id.search_response)
    TextView mMonitor;

    @BindView(R.id.online_number)
    OnlineNumberTextView mOnlineTextView;

    @BindView(R.id.earn_count_textview)
    TextView mEarnThisTimeTextView;

    @BindView(R.id.earn_total_count_textview)
    TextView mEarnTotalAmountTextView;

    @BindView(R.id.user_info_textview)
    TextView mUserInfoTextView;

    @BindView(R.id.prime_try_time_left_textview)
    PrimeTrialTextView mPrimeTrialTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.function_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch (buttonView.getId()) {
                        case R.id.fourg:
                            mRatios[0].setChecked(true);
                            mRatios[1].setChecked(false);
                            mRatios[2].setChecked(false);
                            mRatios[3].setChecked(false);
                            break;
                        case R.id.wifi:
                            mRatios[0].setChecked(false);
                            mRatios[1].setChecked(true);
                            mRatios[2].setChecked(false);
                            mRatios[3].setChecked(false);
                            break;
                        case R.id.auto_choose:
                            mRatios[0].setChecked(false);
                            mRatios[1].setChecked(false);
                            mRatios[2].setChecked(true);
                            mRatios[3].setChecked(false);
                            break;
                        case R.id.hotpoint:
                            mRatios[0].setChecked(false);
                            mRatios[1].setChecked(false);
                            mRatios[2].setChecked(false);
                            mRatios[3].setChecked(true);
                            break;
                    }
                }
            }
        };

        mRatios[0] = (RadioButton) view.findViewById(R.id.fourg);
        mRatios[1] = (RadioButton) view.findViewById(R.id.wifi);
        mRatios[2] = (RadioButton) view.findViewById(R.id.auto_choose);
        mRatios[3] = (RadioButton) view.findViewById(R.id.hotpoint);
        mRatios[0].setOnCheckedChangeListener(listener);
        mRatios[1].setOnCheckedChangeListener(listener);
        mRatios[2].setOnCheckedChangeListener(listener);
        mRatios[3].setOnCheckedChangeListener(listener);

        mRatios[2].setChecked(true);

        BUser user = getModel().getUser();
        if (user != null) {
            mUserInfoTextView.setText(String.format(getString(R.string.format_user_info), user.mUserName, BUser.getPrimeString(user.mPrimeLevel)));
            mPrimeTrialTime.updateView(user);
        }
        mOnlineTextView.updateView();
    }


    @SuppressLint("StringFormatMatches")
    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (response.getData() instanceof BEarn) {
            BEarn earn = (BEarn) response.getData();
            mEarnTotalAmountTextView.setText(String.format(getString(R.string.format_yuan), earn.mEarnAmount));
        }
    }
}
