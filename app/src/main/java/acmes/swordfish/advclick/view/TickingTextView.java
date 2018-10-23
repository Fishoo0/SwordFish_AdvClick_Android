package acmes.swordfish.advclick.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by fishyu on 2018/10/19.
 */

public class TickingTextView extends TextView implements Runnable {

    private long mFrequency = 1 * 1000;

    private boolean mSwitch = false;

    public TickingTextView(Context context) {
        super(context);
    }

    public TickingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TickingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void start() {
        stop();
        mSwitch = true;
        run();

    }

    public void stop() {
        removeCallbacks(this);
        mSwitch = false;
    }

    @Override
    public void run() {
        if (mSwitch) {
            onTicking();
            postDelayed(this, mFrequency);
        }
    }


    protected void onTicking() {

    }

    public void setTickFrequency(long frequency) {
        mFrequency = frequency;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }


}
