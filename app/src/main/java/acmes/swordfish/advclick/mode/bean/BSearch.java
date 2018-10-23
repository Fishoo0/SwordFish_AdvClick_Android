package acmes.swordfish.advclick.mode.bean;

import java.io.Serializable;

/**
 * Created by fishyu on 2018/1/2.
 */

public class BSearch implements Serializable {

    public long mSearchCount;

    public long mClickCount;


    /**
     * 4950-5020
     *
     * @return
     */
    public static final int getOnlineNumber() {
        return (int) (((float) (5020 - 4950) * Math.random()) + 4950.0);
    }


    public long getSearchCount() {
        return mSearchCount;
    }


    public long getClickCount() {
        return mClickCount;
    }

}
