package acmes.swordfish.advclick.mode.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import acmes.swordfish.advclick.SwordFishApplication;
import acmes.swordfish.advclick.R;

/**
 * Created by fishyu on 2018/1/2.
 */

public class BUser implements Serializable {

    @SerializedName("id")
    public String mUserId;

    @SerializedName("name")
    public String mUserName;

    @SerializedName("password")
    public String mUserPassword;

    @SerializedName("cover")
    public String mCover;

    @SerializedName("gender")
    public String mGender;

    @SerializedName("location")
    public String mLocation;

    @SerializedName("about")
    public String mAbout;

    @SerializedName("is_admin")
    public int mIsAdmin;

    @SerializedName("im_qq")
    public String mQQ;

    @SerializedName("telephone")
    public String mTelephone = "";

    @SerializedName("alipay")
    public String mAlipay;

    @SerializedName("alipay_name")
    public String mAlipayName;


    public static final int PRIME_NORMAL = 0;

    public static final int PRIME_TRY = 1;

    public static final int PRIME = 2;

    public static final int PRIME_FORBIDDEN = 3;


    /**
     * level1【普通会员】 用户注册后，默认为level1在有提现金额和提现次数的情况下，可以使用提现功能。但是无法操作功能界面的功能，尤其是功能界面的功能，可以浏览自己的信息和其他版块，看不见隐藏模块。
     * <p>
     * level2【试用会员】后台可以开通试用会员时间，试用会员在开通的时间内，可以使用功能界面的功能，搜索广告，点击广告。然后提现。都是可以的。设定的时间到期后，自动转为level1，试用会员也看不见隐藏模块。
     * <p>
     * level3【正式版会员】
     * 显示隐藏模块，所有功能正常，但是必须填写正确隐藏模块中预设的四个联盟的用户名才行。如果不填写正确的联盟账号，那么仍然无法操作功能界面的点击与搜索。
     * <p>
     * level4【禁止登录】
     * 禁止登录，是有违规的会员，不允许登录软件。
     */
    @SerializedName("prime_level")
    public int mPrimeLevel;

    /**
     * In seconds
     */
    @SerializedName("prime_open_time")
    public long mPrimeOpenTime;

    /**
     * In seconds
     */
    @SerializedName("prime_end_time")
    public long mPrimeEndTime;

    @SerializedName("server_time")
    public long mServerTime;

    @SerializedName("youmeng_checked")
    public int mYoumengChecked;

    public long mLocalTimeSeconds;


    public BUser() {
        mLocalTimeSeconds = System.currentTimeMillis() / 1000;
    }

    public BUser(String user_name, String user_password) {
        mUserName = user_name;
        mUserPassword = user_password;
    }


    public final String getPrimeString() {
        String[] values = SwordFishApplication.getInstance().getResources().getStringArray(R.array.prime_level);
        if (mPrimeLevel < PRIME_NORMAL || mPrimeLevel > PRIME_FORBIDDEN) {
            return values[PRIME_FORBIDDEN];
        } else {
            return values[mPrimeLevel];
        }
    }

    public final long getPrimeTimeLeft() {
        long serverTimeCurrent = (System.currentTimeMillis() / 1000) + (mServerTime - mLocalTimeSeconds);
        long left = mPrimeEndTime - serverTimeCurrent;
        if (left < 0) {
            left = 0;
        }
        return left;
    }

    public String getPrimeTimeLeftString() {
        switch (mPrimeLevel) {
            case PRIME:
                return "无限制";
            case PRIME_TRY:
                return getPrimeTimeLeft() + "秒";
            case PRIME_FORBIDDEN:
            case PRIME_NORMAL:
            default:
                return "屁都没有";
        }

    }

    public String getPrimeTimeLeftHourString() {
        switch (mPrimeLevel) {
            case PRIME:
                return "无限制";
            case PRIME_TRY:
                long seconds = getPrimeTimeLeft();
                long hour = (seconds / 3600) + (seconds % 3600 > 0 ? 1 : 0);
                return hour + "小时";
            case PRIME_FORBIDDEN:
            case PRIME_NORMAL:
            default:
                return "屁都没有";
        }

    }


    public boolean isAdmin() {
        return mIsAdmin > 0;
    }

    /**
     * 只有正式版会员才显示 youmeng
     *
     * @return
     */
    public boolean showYoumeng() {
        return mPrimeLevel == PRIME;
    }


    public boolean isYoumengChecked() {
        return mYoumengChecked > 0;
    }


    public boolean isFullFunction() {
        if (mPrimeLevel == PRIME) {
            if (isYoumengChecked()) {
                return true;
            }
        } else if (mPrimeLevel == PRIME_TRY) {
            if (getPrimeTimeLeft() > 0) {
                return true;
            }
        }
        return false;
    }


}
