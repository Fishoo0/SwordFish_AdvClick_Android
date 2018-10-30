package com.acmes.simpleandroid.utils;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.widget.Toast;

import com.acmes.simpleandroid.mvc.SimpleApplication;

/**
 * Created by fishyu on 2018/1/2.
 */

public class Utils {

    /**
     * 获取本地软件版本号
     */
    public static int getPackageVersionCode() {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = SimpleApplication.getInstance().getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(SimpleApplication.getInstance().getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }


    public static String getIpAddress(Application application) {
        return IPUtils.getIPAddress(true);
    }


    public static String getLocation(Application application) {
        return LocationUtils.getLastLocation(application);
    }


    /**
     * 获取本地软件版本号名称
     */
    public static String getPackageVersionName() {
        String localVersion = "";
        try {
            PackageInfo packageInfo = SimpleApplication.getInstance().getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(SimpleApplication.getInstance().getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }


    /**
     * Show toast
     *
     * @param message
     * @return
     */
    public static final void showToast(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        Toast.makeText(SimpleApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    public static float parseFloat(String value, float defaultValue) {
        try {
            if (!TextUtils.isEmpty(value)) {
                return Float.parseFloat(value);
            }
        } catch (Exception e) {
        }
        return defaultValue;
    }

    public static int parseInt(String value, int defaultValue) {
        try {
            if (!TextUtils.isEmpty(value) && TextUtils.isDigitsOnly(value)) {
                return Integer.parseInt(value);
            }
        } catch (Exception e) {
        }
        return defaultValue;
    }


    public static long parseLong(String value, long defaultValue) {
        try {
            if (!TextUtils.isEmpty(value) && TextUtils.isDigitsOnly(value)) {
                return Long.parseLong(value);
            }
        } catch (Exception e) {
        }
        return defaultValue;
    }


    public static void backToLauncher(Activity context) {
        try {
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 必须加入new task标识
            i.addCategory(Intent.CATEGORY_HOME);
            context.startActivity(i);
        } catch (ActivityNotFoundException e) {
            context.moveTaskToBack(true);
        } catch (SecurityException e) {
            context.moveTaskToBack(true);
        }
    }

}
