package com.wulafu.fsq.application;

import android.app.Application;

/**
 * Created by hellohome on 2016/12/6.
 */

public class WApp extends Application{
    public static WApp mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
