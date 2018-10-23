package acmes.swordfish.advclick.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import acmes.swordfish.advclick.R;

/**
 * Created by fishyu on 2018/10/19.
 */

@SuppressLint("AppCompatCustomView")
public class WithDrawSpinner extends Spinner {
    public WithDrawSpinner(Context context) {
        super(context);
        init();
    }

    public WithDrawSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WithDrawSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.array_withdraw_methods, R.layout.spinner_item);
        setAdapter(adapter);
        setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    Toast.makeText(getContext(), "暂时只支持支付宝", Toast.LENGTH_SHORT).show();
                    setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
