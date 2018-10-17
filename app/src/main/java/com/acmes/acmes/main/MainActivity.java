package com.acmes.acmes.main;

import android.app.Service;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.acmes.acmes.AcmesActivity;
import com.acmes.acmes.R;
import com.acmes.simpleandroid.mvc.ISimpleViewController;
import com.rom4ek.arcnavigationview.ArcNavigationView;

import java.nio.BufferUnderflowException;
import java.util.Vector;
import java.util.concurrent.SynchronousQueue;

import butterknife.BindView;

/**
 * Created by fishyu on 2018/1/2.
 * <p>
 * Two Transactions:
 * 1, {@link MainNavigationView}
 * 2, {@link MainActivityContentView}
 */

public  class MainActivity extends AcmesActivity {

    @BindView(R.id.navigation_view)
    transient ArcNavigationView mNavigationView;

    @BindView(R.id.main_content_view)
    MainActivityContentView mMainContentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //register view controller
        getViewControllerHelper().registerViewController(mMainContentView, savedInstanceState);
        getViewControllerHelper().registerViewController((ISimpleViewController) mNavigationView.getHeaderView(0), savedInstanceState);

        NullPointerException ss;
        IllegalArgumentException sss;
        BufferUnderflowException s;
        ArithmeticException ssss;

        Vector adsf;

        Service service; //实验下 Service 的生命周期

        Bundle bundle = new Bundle();
        getIntent();

    }


}