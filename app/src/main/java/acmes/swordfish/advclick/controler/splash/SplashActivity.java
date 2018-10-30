package acmes.swordfish.advclick.controler.splash;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.acmes.simpleandroid.utils.PermissionUtils;
import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.AdvClickApplication;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.login.DispatcherActivity;
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
            if (PermissionUtils.askForPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET)) {
                DispatcherActivity.jumpToThis(this);
            } else {
                Log.e(TAG, "Start ask for location ...");
            }
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


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean result = PermissionUtils.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
        if (result) {
            DispatcherActivity.jumpToThis(this);
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialog);
            builder.setMessage("您已强制取消权限，请到设置里面设置好权限再启动APP");
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    SplashActivity.this.finish();
                }
            });
            builder.setPositiveButton("退出程序", null);
            builder.create().show();
        }
    }
}
