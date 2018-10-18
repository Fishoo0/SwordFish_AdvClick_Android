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

    @SerializedName("prime_level")
    public int mPrimeLevel;

    @SerializedName("prime_open_time")
    public long mPrimeOpenTime;

    @SerializedName("prime_period")
    public long mPrimePeriod;

    public BUser(String user_name, String user_password) {
        mUserName = user_name;
        mUserPassword = user_password;
    }
}
