package com.acmes.acmes.mode.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fishyu on 2018/2/27.
 */

public class BVideo {
    /**
     * addtime : 1519722375
     * channel : 10
     * dislikes : 0
     * duration : 6505.24
     * embedded_url : https://avgle.com/embed/a19beea993127b98c9d5
     * framerate : 29.97
     * hd : false
     * keyword :
     * likes : 0
     * preview_url : https://static.avgle.com/media/videos/tmb4/138349/1.jpg
     * preview_video_url : https://static.avgle.com/media/videos/tmb4/138349/preview.mp4
     * private : false
     * title :
     * uid : 173186
     * vid : 138349
     * video_url : https://avgle.com/video/138349/sprd-886-代理出産の母-本庄瞳-中文字幕
     * viewnumber : 19
     */

    public String addtime;
    public String channel;
    public String dislikes;
    public String duration;
    public String embedded_url;
    public String framerate;
    public boolean hd;
    public String keyword;
    public String likes;
    @SerializedName("none")
    public String preview_url = "http://c.hiphotos.baidu.com/image/pic/item/2e2eb9389b504fc22f9b0558eedde71191ef6da3.jpg";
    public String preview_video_url;
    @SerializedName("private")
    public boolean privateX;
    public String title;
    public String uid;
    public String vid;
    public String video_url;
    public String viewnumber;
}
