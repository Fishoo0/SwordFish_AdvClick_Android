package acmes.swordfish.advclick.controler.admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.login.SharedPrefAccountManager;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.GetUserRequest;
import acmes.swordfish.advclick.mode.request.UpdateProfileRequest;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/10/17.
 */

public class AdminUpdateProfileActivity extends AdvClickActivity implements View.OnClickListener {

    @BindView(R.id.name_textview)
    TextView mName;

    @BindView(R.id.change_password_edittext)
    TextView mPassword;

    @BindView(R.id.im_qq_edittext)
    EditText mQQ;

    @BindView(R.id.telephone_edittext)
    EditText mPhone;

    @BindView(R.id.alipay)
    EditText mAlipay;

    @BindView(R.id.alipay_name)
    EditText mAlipayName;

    @BindView(R.id.spinner)
    Spinner mPrimeSpinner;

    @BindView(R.id.prime_type_period)
    EditText mPrimePeriod;

    @BindView(R.id.youmeng_checked)
    CheckBox mYoumengChecked;

    private BUser mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_update_profile_activity);

        mUser = (BUser) getIntent().getSerializableExtra("data");

        mPrimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == BUser.PRIME_TRY) {
                    mPrimePeriod.setVisibility(View.VISIBLE);
                    mPrimePeriod.setHint(mUser.getPrimeTimeLeftHourString());
                } else {
                    mPrimePeriod.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        updateView(mUser);
    }

    private void updateView(Object objData) {

        if (objData instanceof BUser) {
            BUser data = (BUser) objData;

            mUser = data;

            mName.setText(data.mUserName);

            mPassword.setText("");
            mQQ.setText("");
            mPhone.setText("");
            mAlipay.setText("");
            mAlipayName.setText("");

            mPrimeSpinner.setSelection(mUser.mPrimeLevel);

            if (mUser.mPrimeLevel == BUser.PRIME_TRY) {
                mPrimePeriod.setVisibility(View.VISIBLE);
                mPrimePeriod.setText("");
                mPrimePeriod.setHint(data.getPrimeTimeLeftHourString());
            } else {
                mPrimePeriod.setVisibility(View.GONE);
                mPrimePeriod.setHint("");
            }

            mPassword.setHint(data.mUserPassword);
            mQQ.setHint(data.mQQ);
            mPhone.setHint(data.mTelephone);
            mAlipay.setHint(data.mAlipay);
            mAlipayName.setHint(data.mAlipayName);

            mYoumengChecked.setChecked(data.isYoumengChecked());
        }
    }

    @OnClick(R.id.with_draw_request_button)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.with_draw_request_button:
                UpdateProfileRequest request = new UpdateProfileRequest(
                        mUser.mUserId,
                        mPassword.getText().toString(),
                        mQQ.getText().toString(),
                        mPhone.getText().toString(),
                        mAlipay.getText().toString(),
                        mAlipayName.getText().toString(),
                        mPrimeSpinner.getSelectedItemPosition(),
                        mPrimePeriod.getText().toString(),
                        mYoumengChecked.isChecked()
                );
                getModel().performRequest(request);
                break;
        }
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (response.isSuccess()) {
            updateView(response.getData());
        }

        if (response.getData() instanceof BUser) {
            BUser user = (BUser) response.getData();
            // if is current user, update
            if (user.mUserId.equals(SharedPrefAccountManager.getInstance().getCurrentUser().mUserId)) {
                SharedPrefAccountManager.getInstance().updateUser(user);
            }
        }

        if (request instanceof UpdateProfileRequest || !response.isSuccess()) {
            Utils.showToast(response.getMessage());
        }
    }


    @Override
    public void onRefresh() {
        super.onRefresh();

        GetUserRequest request = new GetUserRequest(mUser.mUserId);
        getModel().performRequest(request);
    }

    public static final void jumpToThis(Context context, BUser data) {
        Intent intent = new Intent(context, AdminUpdateProfileActivity.class);
        intent.putExtra("data", data);
        context.startActivity(intent);
    }
}
