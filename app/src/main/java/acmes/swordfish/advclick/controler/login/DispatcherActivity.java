package acmes.swordfish.advclick.controler.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import acmes.swordfish.advclick.SwordFishActivity;
import acmes.swordfish.advclick.controler.main.MainActivity;
import acmes.swordfish.advclick.mode.bean.BUser;
import acmes.swordfish.advclick.mode.request.LoginRequest;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 * <p>
 * This class would never be destroyed for dispatching.
 */
public class DispatcherActivity extends SwordFishActivity<LoginMode> {

    public static final int CMD_AUTO = 0;
    public static final int CMD_LOGOUT = 1;
    public static final int CMD_LOGIN = 2;
    public static final int CMD_CLOSE_APP = 3;

    public static final String KEY_CMD = "cmd";


    IAccountManager mAccountManager = SharedPrefAccountManager.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dispatch(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        dispatch(intent);
    }

    /**
     * Dispatch Activity
     */
    protected void dispatch(Intent intent) {
        Log.e(TAG, "dispatch");
        int cmd = intent.getIntExtra(KEY_CMD, CMD_AUTO);
        switch (cmd) {
            case CMD_LOGOUT:
                LogoutActivity.jumpToThis(DispatcherActivity.this);
                break;
            case CMD_CLOSE_APP:
                finish();
                break;
            case CMD_LOGIN:
            case CMD_AUTO:
            default:
                if (mAccountManager.getCurrentUser() == null || intent.hasExtra(LoginActivity.LOGIN_INFO)) {
                    Log.v(TAG, "\t no current user, goto LoginActivity ");
                    //no current user ,goto login activity
                    startActivity(new Intent(DispatcherActivity.this, LoginActivity.class).putExtras(intent));
                } else {
                    Log.v(TAG, "\t refresh login status, goto MainActivity");
                    getModel().login(new LoginRequest(mAccountManager.getCurrentUser().mUserName));
                    startActivity(new Intent(DispatcherActivity.this, MainActivity.class));
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
                DispatcherActivity.jumpToThisForLogout(this);
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

    public static final void jumpToThisForLogout(Context context) {
        jumpToThis(context, CMD_LOGOUT);
    }

    public static final void jumpToThisForLogin(Context context, BUser user) {
        if (user == null) {
            throw new IllegalArgumentException("BUse must not be null !");
        }
        Intent intent = getJumpToThisIntent(context);
        intent.putExtra(LoginActivity.LOGIN_INFO, user);
        jumpToThis(context, CMD_LOGIN, intent);
    }


    private static final void jumpToThis(Context context, int cmd) {
        jumpToThis(context, cmd, getJumpToThisIntent(context));
    }

    private static final void jumpToThis(Context context, int cmd, Intent intent) {
        if (!intent.hasExtra(KEY_CMD)) {
            intent.putExtra(KEY_CMD, cmd);
        }
        context.startActivity(intent);
    }

    private static final Intent getJumpToThisIntent(Context context) {
        Intent intent = new Intent(context, DispatcherActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

}
