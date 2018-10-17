package com.acmes.acmes.mode.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fishyu on 2018/2/27.
 */

public class BCategory {

    public String CHID;
    public String name;
    public String slug;
    public String total_videos;
    public String category_url;

    @SerializedName("none")
    public String cover_url = "http://c.hiphotos.baidu.com/image/pic/item/2e2eb9389b504fc22f9b0558eedde71191ef6da3.jpg";
}
