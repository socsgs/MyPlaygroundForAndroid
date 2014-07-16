package com.socs.myplayground.ui;

import android.app.Application;
import android.util.Log;

/**
 * Created by SocsDrive on 7/11/2014.
 */
public class MyApplication extends Application {
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate");
    }
}
