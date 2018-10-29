package acmes.swordfish.advclick.mode.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class BEarn implements Serializable {

    // 从未提现
    public static final int REQUEST_WITHDRAW_NONE = -1;

    //提现成功
    public static final int REQUEST_WITHDRAW_FINISHED = 0;


    @SerializedName("id")
    public String mId;

    @SerializedName("earn_amount")
    public float mEarnAmount;

    @SerializedName("with_draw_times_left")
    public int mWithDrawTimesLeft;

    @SerializedName("request_with_draw_amount")
    public float mLastRequestAmount;

    @SerializedName("request_with_draw_time")
    public String mLastRequestTime;

    @SerializedName("manager_with_draw_amount")
    public float mLastManagerResponseAmount;

    @SerializedName("manager_with_draw_time")
    public float mLastManagerResponseTime;

    public String getEarnAmountString() {
        return String.format("%.04f", mEarnAmount);
    }

    public String getRequestWithDrawAmountString() {
        return String.format("%.04f", mLastRequestAmount);
    }

}
