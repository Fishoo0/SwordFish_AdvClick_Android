package acmes.swordfish.advclick.controler.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.mode.bean.BSearch;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.LoginRequest;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/1/2.
 */

public class LoginActivity extends AdvClickActivity<LoginMode> implements View.OnClickListener {

    @Override
    protected LoginMode createModel() {
        return new LoginMode();
    }

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.user_name)
    protected EditText mUserName;

    @BindView(R.id.user_password)
    protected EditText mUserPassword;

    @BindView(R.id.online_number)
    TextView mOnlineNumber;

    public static final String LOGIN_INFO = "user";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mSwipeRefreshLayout.setEnabled(false);


        if (getIntent().hasExtra(LOGIN_INFO)) {
            final BUser user = (BUser) getIntent().getSerializableExtra(LOGIN_INFO);
            getModel().login(new LoginRequest(user.mUserName, user.mUserPassword));

            mUserName.setText(user.mUserName);
            mUserPassword.setText(user.mUserPassword);
        }


        mOnlineNumber.setText(String.format(getString(R.string.format_login_online), String.valueOf(BSearch.getOnlineNumber())));


        getSupportActionBar().setTitle("登陆");
    }


    @Override
    public void onRequestStart(SimpleRequest request) {
        super.onRequestStart(request);

        mSwipeRefreshLayout.setRefreshing(true);
    }


    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (response.isSuccess()) {
            DispatcherActivity.jumpToThis(this);
            finish();
        }

        Utils.showToast(response.getMessage());
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        super.onFailure(request, exception);
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                getModel().login(new LoginRequest(mUserName.getText().toString(), mUserPassword.getText().toString()));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.goto_register_button, R.id.forget_password})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goto_register_button:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.forget_password:
                Toast.makeText(this, "请尝试联系管理员", Toast.LENGTH_LONG).show();
                break;
        }
    }


    @Override
    public void onBackPressed() {
        Utils.backToLauncher(this);
    }

}
