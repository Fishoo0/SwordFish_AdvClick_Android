package acmes.swordfish.advclick.mode.bean;

import java.io.Serializable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class BSearch implements Serializable {

    public int mSearchCount;
    public int mClickCount;
    public float mLastEarn;
    public float mEarn;
    public float mEarnUploaded;


    /**
     * 4950-5020
     *
     * @return
     */
    public static final int getOnlineNumber() {
        return (int) (((float) (5020 - 4950) * Math.random()) + 4950.0);
    }

    /**
     * 每2秒搜索180~220
     *
     * @return
     */
    public int getSearchCount() {
        return mSearchCount;
    }


    /**
     * 获取点击次数
     *
     * @return
     */
    public int getClickCount() {
        return mClickCount;
    }


    /**
     * 每15秒点击一次搜索的广告，每次点击生成收益并计算进收入和余额中，每次点击的收入为0.02-0.06之间的随机值 其中0.05-0.06显示为高额广告
     *
     * @return
     */
    public float getEarn() {
        return mEarn;
    }

    public String getEarnString() {
        return String.format("%.04f", mEarn);
    }

    public float getLastEarn() {
        return mLastEarn;
    }


    public boolean clickable() {
        return getClickCount() < getSearchCount();
    }
}
