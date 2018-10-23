package acmes.swordfish.advclick.mode.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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

    @SerializedName("im_qq")
    public String mQQ;

    @SerializedName("alipay")
    public String mAlipay;

    @SerializedName("alipay_name")
    public String mAlipayName;


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

    @SerializedName("prime_open_time")
    public long mPrimeOpenTime;

    @SerializedName("prime_end_time")
    public long mPrimeEndTime;

    public BUser(String user_name, String user_password) {
        mUserName = user_name;
        mUserPassword = user_password;
    }

    public BEarn mEarn;

    public static final String getPrimeString(int primeLevel) {
        switch (primeLevel) {
            case 1:
                return "普通会员";
            case 2:
                return "试用会员";
            case 3:
                return "正式版会员";
            case 4:
            default:
                return "禁止登陆";
        }
    }

    public long getEndTime() {
//        return mPrimeEndTime;
        if (mPrimeOpenTime == 0) {
            mPrimeOpenTime = System.currentTimeMillis() + 30 * 1000;
        }
        return mPrimeOpenTime;
    }


    public final long getPrimeTimeLeft() {
        long second = (getEndTime() - System.currentTimeMillis()) / 1000;
        if (second < 0) {
            return 0;
        }
        return second;
    }


}
