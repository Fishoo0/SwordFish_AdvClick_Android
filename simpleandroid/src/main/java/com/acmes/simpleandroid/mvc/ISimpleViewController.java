package com.acmes.simpleandroid.mvc;

import android.os.Bundle;

/**
 * Created by fishyu on 2018/2/27.
 */

public interface ISimpleViewController<T> extends ISimpleModeCallback {

    void registered();

    /**
     * No use actually
     *
     * @param savedInstance
     */
    @Deprecated
    void onCreate(Bundle savedInstance);

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();

    void unregistered();

    T getMasterContext();

}
