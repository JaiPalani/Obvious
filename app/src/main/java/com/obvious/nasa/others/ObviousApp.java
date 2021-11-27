package com.obvious.nasa.others;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by Jai on 27/11/2021.
 * Location India
 */

public class ObviousApp extends Application {
    private static final String TAG = "ECFANAPP";
    private static ObviousApp m_instance;

    public static ObviousApp getInstance() {
        return m_instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        m_instance = this;
        initInstance();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ObviousLog.v(TAG, "onConfigurationChanged()");
    }

    private void initInstance() {
        ObviousLog.v(TAG, "initInstance()");
    }

}
