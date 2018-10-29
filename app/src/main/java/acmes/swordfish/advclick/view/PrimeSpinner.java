package acmes.swordfish.advclick.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import acmes.swordfish.advclick.R;

/**
 * Created by fishyu on 2018/10/19.
 */

@SuppressLint("AppCompatCustomView")
public class PrimeSpinner extends Spinner {
    public PrimeSpinner(Context context) {
        super(context);
        init();
    }

    public PrimeSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PrimeSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getContext(), R.array.prime_level, R.layout.spinner_item);
        setAdapter(adapter);
        setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}
