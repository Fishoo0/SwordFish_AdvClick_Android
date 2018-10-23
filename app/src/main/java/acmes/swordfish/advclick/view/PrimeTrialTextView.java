package acmes.swordfish.advclick.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.mode.bean.BUser;

/**
 * Created by fishyu on 2018/10/19.
 */

public class PrimeTrialTextView extends TickingTextView {

    public PrimeTrialTextView(Context context) {
        super(context);
        init();
    }

    public PrimeTrialTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PrimeTrialTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setTickFrequency(1 * 1000);
    }

    @Override
    public void onTicking() {
        super.onTicking();
        setText(String.format(getResources().getString(R.string.format_prime_time_left), String.valueOf(mUser.getPrimeTimeLeft())));
    }

    private BUser mUser;

    public void updateView(BUser user) {
        mUser = user;
        start();
    }


    @Override
    public void start() {
        if (mUser == null) {
            return;
        }
        super.start();
    }
}
