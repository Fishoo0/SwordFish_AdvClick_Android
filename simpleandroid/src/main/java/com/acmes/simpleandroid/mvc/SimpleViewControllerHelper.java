package com.acmes.simpleandroid.mvc;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.acmes.simpleandroid.mvc.model.SimpleRequest;
import com.acmes.simpleandroid.mvc.model.SimpleResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fishyu on 2018/2/27.
 */

public class SimpleViewControllerHelper implements ISimpleViewController {

    private List<ISimpleViewController> mViewControllers = new ArrayList<>();

    private final class InternalHandler extends Handler {

        static final int CMD_REGISTER = 1;
        static final int CMD_UNREGISTER = 2;

        static final int CMD_DESTROY = 3;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CMD_REGISTER:
                    mViewControllers.remove(msg.obj);
                    mViewControllers.add((ISimpleViewController) msg.obj);
                    ((ISimpleViewController) msg.obj).registered();

                    break;
                case CMD_UNREGISTER:
                    mViewControllers.remove(msg.obj);
                    ((ISimpleViewController) msg.obj).unregistered();
                    break;
                case CMD_DESTROY:
                    mViewControllers.clear();
                    for (ISimpleViewController controller : mViewControllers) {
                        controller.unregistered();
                    }
                    break;
            }
        }
    }

    private InternalHandler mInternalHandler = new InternalHandler();

    public void registerViewController(final ISimpleViewController viewController, final Bundle bundle) {
        mInternalHandler.sendMessage(Message.obtain(mInternalHandler, InternalHandler.CMD_REGISTER, viewController));
        //temp adding
        mInternalHandler.post(new Runnable() {
            @Override
            public void run() {
                viewController.onCreate(bundle);
            }
        });
    }

    public void unregisterViewController(ISimpleViewController viewController) {
        mInternalHandler.sendMessage(Message.obtain(mInternalHandler, InternalHandler.CMD_UNREGISTER, viewController));
    }

    private void unregisterAll() {
        mInternalHandler.sendMessage(Message.obtain(mInternalHandler, InternalHandler.CMD_DESTROY));
    }

    @Override
    public void registered() {

    }

    @Override
    public void onCreate(Bundle savedInstance) {
        for (ISimpleViewController controller : mViewControllers) {
            controller.onCreate(savedInstance);
        }
    }

    @Override
    public void onStart() {
        for (ISimpleViewController controller : mViewControllers) {
            controller.onStart();
        }
    }

    @Override
    public void onResume() {
        for (ISimpleViewController controller : mViewControllers) {
            controller.onResume();
        }
    }

    @Override
    public void onPause() {
        for (ISimpleViewController controller : mViewControllers) {
            controller.onPause();
        }
    }

    @Override
    public void onStop() {
        for (ISimpleViewController controller : mViewControllers) {
            controller.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (ISimpleViewController controller : mViewControllers) {
            controller.onDestroy();
        }
        unregisterAll();
    }

    @Override
    public void unregistered() {

    }

    @Override
    public Object getMasterContext() {
        return null;
    }


    @Override
    public void onRequestStart(SimpleRequest request) {
        for (ISimpleViewController controller : mViewControllers) {
            controller.onRequestStart(request);
        }
    }

    @Override
    public void onResponse(SimpleRequest request, SimpleResponse response) {
        for (ISimpleViewController controller : mViewControllers) {
            controller.onResponse(request, response);
        }
    }

    @Override
    public void onFailure(SimpleRequest request, Throwable exception) {
        for (ISimpleViewController controller : mViewControllers) {
            controller.onFailure(request, exception);
        }
    }

}
