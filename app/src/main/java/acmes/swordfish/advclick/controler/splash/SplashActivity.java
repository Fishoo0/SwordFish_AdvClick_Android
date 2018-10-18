package acmes.swordfish.advclick.controler.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.AdvClickApplication;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.login.DispatcherActivity;
import com.acmes.simpleandroid.utils.Utils;

import butterknife.BindView;
import hugo.weaving.DebugLog;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SplashActivity extends AdvClickActivity implements AdvClickApplication.IInitializeListener {

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
        AdvClickApplication.getInstance().registerInitializeListener(SplashActivity.this);
    }

    @Override
    public void onInitializing(int progress) {
        Log.e(TAG, "onInitializing -> " + progress);
//        mInitialProgress.setText(progress + " %");
        if (progress == 100) {
            DispatcherActivity.jumpToThis(this);
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
        AdvClickApplication.getInstance().unregisterInitializeListener(SplashActivity.this);
    }

}
