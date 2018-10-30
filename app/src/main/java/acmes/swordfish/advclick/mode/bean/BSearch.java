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
