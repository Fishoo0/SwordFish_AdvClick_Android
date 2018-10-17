package com.acmes.acmes.login;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.acmes.acmes.AcmesApplication;
import com.acmes.acmes.mode.bean.BUser;
import com.google.gson.Gson;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SharedPrefAccountManager implements IAccountManager {

    private static final String TAG = SharedPrefAccountManager.class.getSimpleName();

    private static SharedPrefAccountManager mInstance = new SharedPrefAccountManager(AcmesApplication.getInstance());

    private static final String ACCOUNT = "Account";
    private static final String KEY_ACCOUNT = "key_account";

    private static final Gson GSON = new Gson();
    private SharedPreferences mSharedPreferences;

    private BUser mCurrentUser;

    public SharedPrefAccountManager(Application context) {
        mSharedPreferences = context.getSharedPreferences(ACCOUNT, Context.MODE_PRIVATE);
        String user = mSharedPreferences.getString(KEY_ACCOUNT, "");
        Log.e(TAG, "user -> " + user);
        if (!TextUtils.isEmpty(user)) {
            mCurrentUser = new Gson().fromJson(user, BUser.class);
        }
    }

    public static final SharedPrefAccountManager getInstance() {
        return mInstance;
    }

    @Override
    public BUser getCurrentUser() {
        return mCurrentUser;
    }

    @Override
    public void addUser(BUser user) {
        String userString = GSON.toJson(user);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_ACCOUNT, userString);
        editor.commit();
        mCurrentUser = user;
    }

    @Override
    public void removeUser(BUser user) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(KEY_ACCOUNT);
        editor.commit();
        mCurrentUser = null;
    }


}
