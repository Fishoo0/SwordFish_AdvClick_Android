package acmes.swordfish.advclick.controler.function;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.main.MainContentFragment;
import acmes.swordfish.advclick.mode.bean.BEarn;
import acmes.swordfish.advclick.mode.bean.BSearch;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.ClickRequest;
import acmes.swordfish.advclick.mode.request.SearchRequest;
import acmes.swordfish.advclick.mode.request.UploadEarnRequest;
import acmes.swordfish.advclick.view.OnlineNumberTextView;
import acmes.swordfish.advclick.view.PrimeTrialTextView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/10/17.
 */

public class FunctionFragment extends MainContentFragment implements View.OnClickListener {

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

    @BindView(R.id.start_search_button)
    Button mSearchButton;

    @BindView(R.id.start_click_button)
    Button mClickButton;

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
            mUserInfoTextView.setText(String.format(getString(R.string.format_user_info), user.mUserName, user.getPrimeString()));
            mPrimeTrialTime.updateView(user);
        }
        mOnlineTextView.updateView();

        mMonitor.setMovementMethod(ScrollingMovementMethod.getInstance());
    }


    @SuppressLint("StringFormatMatches")
    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (response.getData() instanceof BEarn) {
            BEarn earn = (BEarn) response.getData();
            mEarnTotalAmountTextView.setText(String.format(getString(R.string.format_yuan), earn.getEarnAmountString()));
        }


        if (request instanceof SearchRequest) {
            if (response.isSuccess()) {
                BSearch data = (BSearch) response.getData();
                mMonitor.append("已搜索广告 " + data.getSearchCount() + " 条" + "\n");
            } else {
                mSearchButton.performClick();
            }

        } else if (request instanceof ClickRequest) {
            if (response.isSuccess()) {
                BSearch data = (BSearch) response.getData();
                mMonitor.append("已点击 " + data.getClickCount() + " 条" + "\n");
                mEarnThisTimeTextView.setText(String.format(getResources().getString(R.string.format_yuan), data.getEarnString()));
            } else {
                mClickButton.performClick();
            }
        }

        if (request instanceof SearchRequest || request instanceof ClickRequest || request instanceof UploadEarnRequest) {
            if (!response.isSuccess()) {
                Utils.showToast(response.getMessage());
            }
        }
    }

    @OnClick({R.id.start_search_button, R.id.start_click_button})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_search_button: {
                if (mClickButton.getText().equals(getString(R.string.value_stop_click))) {
                    Utils.showToast("点击时不能搜索");
                    return;
                }
                if (((Button) v).getText().equals(getString(R.string.value_start_search))) {
                    ((Button) v).setText(R.string.value_stop_search);
                    // start search

                    mMonitor.append("开始搜索广告 \n");

                    getModel().startSearch();
                } else {
                    ((Button) v).setText(R.string.value_start_search);
                    // stop search

                    mMonitor.append("停止搜索广告 \n");
                    getModel().stopSearch();
                }
            }
            break;
            case R.id.start_click_button: {
                if (mSearchButton.getText().equals(getString(R.string.value_stop_search))) {
                    Utils.showToast("搜索时不能点击");
                    return;
                }
                if (((Button) v).getText().equals(getString(R.string.value_start_click))) {
                    ((Button) v).setText(R.string.value_stop_click);
                    // start click
                    mMonitor.append("开始点击广告 \n");
                    getModel().startClick();
                } else {
                    ((Button) v).setText(R.string.value_start_click);
                    // stop click
                    mMonitor.append("停止搜索广告 \n");
                    getModel().stopClick();
                }
            }
            break;
        }
    }


}
