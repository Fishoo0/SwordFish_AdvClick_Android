package acmes.swordfish.advclick.controler.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.RegisterRequest;
import butterknife.BindView;

/**
 * Created by fishyu on 2018/1/2.
 */

public class RegisterActivity extends AdvClickActivity<LoginMode> {

    @Override
    protected LoginMode createModel() {
        return new LoginMode();
    }

    @BindView(R.id.user_name)
    EditText mUserName;

    @BindView(R.id.user_password)
    EditText mUserPassword;

    @BindView(R.id.im_qq)
    EditText mQQ;

    @BindView(R.id.alipay)
    EditText mAlipay;

    @BindView(R.id.alipay_name)
    EditText mAlipayName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        getSupportActionBar().setTitle("注 册");

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
                DispatcherActivity.jumpToThis(
                        this,
                        DispatcherActivity.CMD_LOGIN,
                        DispatcherActivity
                                .getJumpToThisIntent(this)
                                .putExtra(LoginActivity.LOGIN_INFO,
                                        new BUser(((RegisterRequest) request).name, ((RegisterRequest) request).password))
                );
                finish();
            }
        }
        Utils.showToast(response.getMessage());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_login:
                getModel().register(new RegisterRequest(
                        mUserName.getText().toString(),
                        mUserPassword.getText().toString(),
                        mQQ.getText().toString(),
                        mAlipay.getText().toString(),
                        mAlipayName.getText().toString()));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
