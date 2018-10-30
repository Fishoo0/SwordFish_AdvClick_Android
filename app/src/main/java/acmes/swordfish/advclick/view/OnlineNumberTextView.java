package acmes.swordfish.advclick.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.mode.bean.BSearch;

/**
 * Created by fishyu on 2018/10/19.
 */

public class OnlineNumberTextView extends TickingTextView {

    public OnlineNumberTextView(Context context) {
        super(context);
        init();
    }

    public OnlineNumberTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OnlineNumberTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setTickFrequency(15 * 1000);
    }

    @Override
    public void onTicking() {
        super.onTicking();
        setText(String.format(getResources().getString(R.string.format_login_online), String.valueOf(BSearch.getOnlineNumber())));

    }

    public void updateView() {
        start();
    }


}
