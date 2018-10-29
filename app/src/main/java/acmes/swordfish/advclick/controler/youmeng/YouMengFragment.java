package acmes.swordfish.advclick.controler.youmeng;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.main.MainContentFragment;
import acmes.swordfish.advclick.mode.request.CheckYoumengRequest;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by fishyu on 2018/10/17.
 */

public class YouMengFragment extends MainContentFragment implements View.OnClickListener {


    @BindView(R.id.baidu_edittext)
    EditText mBaidu;

    @BindView(R.id.google_edittext)
    EditText mGoogle;

    @BindView(R.id.sougou_edittext)
    EditText mSougou;

    @BindView(R.id.taobao_edittext)
    EditText mTaobao;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.youmeng_fragment, null);
    }

    @OnClick(R.id.check_youmeng)
    @Override
    public void onClick(View v) {
        CheckYoumengRequest request = new CheckYoumengRequest(getModel().getUser().mUserId,
                mBaidu.getText().toString(),
                mGoogle.getText().toString(),
                mSougou.getText().toString(),
                mTaobao.getText().toString());
        getModel().performRequest(request);
    }
}
