package acmes.swordfish.advclick.controler.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.login.DispatcherActivity;
import acmes.swordfish.advclick.controler.main.MainContentFragment;
import acmes.swordfish.advclick.mode.request.LogoutRequest;
import acmes.swordfish.advclick.mode.request.UpdateProfileRequest;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/10/17.
 */

public class ProfileFragment extends MainContentFragment implements View.OnClickListener {


    @BindView(R.id.name_textview)
    TextView mName;

    @BindView(R.id.prime_type_textview)
    TextView mPrime;

    @BindView(R.id.alipay)
    TextView mAlipay;

    @BindView(R.id.alipay_name)
    TextView mAlipayName;


    @BindView(R.id.change_password_edittext)
    EditText mPassword;

    @BindView(R.id.im_qq_edittext)
    EditText mQQ;

    @BindView(R.id.telephone_edittext)
    EditText mPhone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set listeners
//        view.findViewById(R.id.logout).setOnClickListener(this);

        updateView();
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    private void updateView() {
        mName.setText(getModel().getUser().mUserName);
        mPrime.setText(getModel().getUser().getPrimeString());
        mAlipay.setText(getModel().getUser().mAlipay);
        mAlipayName.setText(getModel().getUser().mAlipayName);


        mPassword.setText("");
        mQQ.setText("");
        mPhone.setText("");

        mQQ.setHint(getModel().getUser().mQQ);
        mPhone.setHint(getModel().getUser().mTelephone);


    }

    @OnClick({R.id.with_draw_request_button, R.id.logout})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.with_draw_request_button:
                UpdateProfileRequest request = new UpdateProfileRequest(getModel().getUser().mUserId,
                        mPassword.getText().toString(),
                        mQQ.getText().toString(),
                        mPhone.getText().toString());
                getModel().performRequest(request);
                break;
            case R.id.logout:

                LogoutRequest logoutRequest = new LogoutRequest(getModel().getUser().mUserId);
                getModel().performRequest(logoutRequest);

                DispatcherActivity.jumpToThisForLogout(getContext());
                break;
        }
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);

        if (request instanceof UpdateProfileRequest) {
            if (response.isSuccess()) {
                updateView();
            }

            if (!TextUtils.isEmpty(((UpdateProfileRequest) request).password)) {
                DispatcherActivity.jumpToThisForLogin(getContext(), getModel().getUser());
            }

            Utils.showToast(response.getMessage());
        }


    }
}
