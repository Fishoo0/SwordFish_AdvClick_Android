package acmes.swordfish.advclick.controler.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.acmes.simpleandroid.utils.Utils;

import acmes.swordfish.advclick.AdvClickActivity;
import acmes.swordfish.advclick.R;
import acmes.swordfish.advclick.controler.main.MainActivity;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 */

public class AdminChooseActivity extends AdvClickActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.super_view:
                startActivity(new Intent(this, AdminUserListActivity.class));
                break;
            case R.id.normal_view:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


    @Override
    public void onBackPressed() {
        Utils.backToLauncher(this);
    }
}