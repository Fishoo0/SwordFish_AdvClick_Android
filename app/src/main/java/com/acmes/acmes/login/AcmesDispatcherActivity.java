package com.acmes.acmes.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.acmes.acmes.AcmesActivity;
import com.acmes.acmes.main.MainActivity;
import com.acmes.acmes.mode.request.LoginRequest;
import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 * <p>
 * This class would never be destroyed for dispatching.
 */
public class AcmesDispatcherActivity extends AcmesActivity<LoginMode> {

    public static final int CMD_AUTO = 0;
    public static final int CMD_LOGOUT = 1;
    public static final int CMD_LOGIN = 2;
    public static final int CMD_CLOSE_APP = 3;

    public static final String KEY_CMD = "cmd";

    IAccountManager mAccountManager = SharedPrefAccountManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatch();
    }

    /**
     * Dispatch Activity
     */
    protected void dispatch() {
        Log.e(TAG, "dispatch");
        int cmd = getIntent().getIntExtra(KEY_CMD, CMD_AUTO);
        switch (cmd) {
            case CMD_LOGOUT:
                LogoutActivity.jumpToThis(AcmesDispatcherActivity.this);
                break;
            case CMD_CLOSE_APP:
                finish();
                break;
            case CMD_LOGIN:
            case CMD_AUTO:
            default:
                if (mAccountManager.getCurrentUser() == null) {
                    Log.v(TAG, "\t no current user, goto LoginActivity ");
                    //no current user ,goto login activity
                    startActivity(new Intent(AcmesDispatcherActivity.this, LoginActivity.class).putExtras(getIntent()));
                } else {
                    Log.v(TAG, "\t refresh login status, goto MainActivity");
                    startActivity(new Intent(AcmesDispatcherActivity.this, MainActivity.class));
                    getModel().login(new LoginRequest(mAccountManager.getCurrentUser().mUserName));
                }
                break;
        }

        //speed it up
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        finish();
    }

    @Override
    protected LoginMode createModel() {
        return LoginMode.getInstance();
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        super.onResponse(request, response);
        if (request instanceof LoginRequest) {
            if (!(response.isSuccess())) {
                Log.e(TAG, "\t login failed, jump to this for further processing");
                Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
                jumpToThis(this);
            } else {
                // Login successfully
            }
        }
    }

    /**
     * When token expired, call this method for re-dispatch
     *
     * @param context
     */
    public static final void jumpToThis(Context context) {
        jumpToThis(context, CMD_AUTO, getJumpToThisIntent(context));
    }

    public static final void jumpToThis(Context context, int cmd) {
        jumpToThis(context, cmd, getJumpToThisIntent(context));
    }

    public static final void jumpToThis(Context context, int cmd, Intent intent) {
        if (!intent.hasExtra(KEY_CMD)) {
            intent.putExtra(KEY_CMD, cmd);
        }
        context.startActivity(intent);
    }

    public static final Intent getJumpToThisIntent(Context context) {
        Intent intent = new Intent(context, AcmesDispatcherActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

}
