package acmes.swordfish.advclick.mode.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class BLog implements Serializable {

    @SerializedName("operation")
    public String mOperation;

    @SerializedName("device")
    public String mDevice;

    @SerializedName("ip")
    public String mNetwork;

    @SerializedName("location")
    public String mLocation;

    @SerializedName("time")
    public String mTime;


    @SerializedName("user")
    public BUser mUser;

}
