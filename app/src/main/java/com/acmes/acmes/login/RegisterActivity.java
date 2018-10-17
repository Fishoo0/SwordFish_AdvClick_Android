package com.acmes.acmes.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.acmes.acmes.AcmesActivity;
import com.acmes.acmes.R;
import com.acmes.acmes.mode.bean.BUser;
import com.acmes.acmes.mode.request.RegisterRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import butterknife.BindView;

/**
 * Created by fishyu on 2018/1/2.
 */

public class RegisterActivity extends AcmesActivity<LoginMode> {

    @Override
    protected LoginMode createModel() {
        return new LoginMode();
    }

    @BindView(R.id.user_name)
    EditText mUserName;


    @BindView(R.id.user_password)
    EditText mUserPassword;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        getSupportActionBar().setTitle("Register");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_login, menu);
        return true;
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (request instanceof RegisterRequest) {
            if (response.isSuccess()) {
                AcmesDispatcherActivity.jumpToThis(
                        this,
                        AcmesDispatcherActivity.CMD_LOGIN,
                        AcmesDispatcherActivity
                                .getJumpToThisIntent(this)
                                .putExtra(LoginActivity.LOGIN_INFO,
                                        new BUser(((RegisterRequest) request).user_name, ((RegisterRequest) request).user_password))
                );
                finish();
            }
        }
        Utils.showToast(response.getMessage() + " " + response.getData());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                getModel().register(new RegisterRequest(mUserName.getText().toString(), mUserPassword.getText().toString()));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
