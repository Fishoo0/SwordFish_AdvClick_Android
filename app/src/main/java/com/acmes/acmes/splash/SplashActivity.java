package com.acmes.acmes.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.acmes.acmes.AcmesActivity;
import com.acmes.acmes.AcmesApplication;
import com.acmes.acmes.AcmesMode;
import com.acmes.acmes.R;
import com.acmes.acmes.login.AcmesDispatcherActivity;
import com.acmes.simpleandroid.mvc.model.SimpleModel;
import com.acmes.simpleandroid.utils.Utils;

import butterknife.BindView;
import hugo.weaving.DebugLog;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SplashActivity extends AcmesActivity implements AcmesApplication.IInitializeListener {

    @BindView(R.id.version)
    TextView mTextView;

    @BindView(R.id.imageview)
    ImageView mImageView;

    @BindView(R.id.progress)
    TextView mInitialProgress;

    @DebugLog
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        mTextView.setText(String.format(getString(R.string.value_splash_version), Utils.getPackageVersionName()));
        AcmesApplication.getInstance().registerInitializeListener(SplashActivity.this);
    }

    @Override
    public void onInitializing(int progress) {
        Log.e(TAG, "onInitializing -> " + progress);
//        mInitialProgress.setText(progress + " %");
        if (progress == 100) {
            AcmesDispatcherActivity.jumpToThis(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AcmesApplication.getInstance().unregisterInitializeListener(SplashActivity.this);
    }

}
