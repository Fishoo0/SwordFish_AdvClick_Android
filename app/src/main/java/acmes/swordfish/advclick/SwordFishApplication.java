package acmes.swordfish.advclick;

import com.acmes.simpleandroid.imp.Square.SquareNetwork;
import com.acmes.simpleandroid.mvc.SimpleApplication;
import com.squareup.picasso.Picasso;

import hugo.weaving.DebugLog;
import retrofit2.Retrofit;

/**
 * Created by fishyu on 2018/1/2.
 */

public class SwordFishApplication extends SimpleApplication {

    protected SquareNetwork mSquareNetwork;

    public static SwordFishApplication getInstance() {
        return (SwordFishApplication) SimpleApplication.getInstance();
    }

    public static Picasso getPicasso() {
        return getInstance().mSquareNetwork.getPicasso();
    }

    @DebugLog
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        mSquareNetwork = new SquareNetwork(this);
        updateProgress(20);
        while (getInitializeProgress() < 100) {
            try {
                Thread.sleep((long) (Math.random() * 10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateProgress(getInitializeProgress() + 1);
        }
    }

    public Retrofit buildRetrofit(String url) {
        return mSquareNetwork.buildRetrofit(url);
    }

}
