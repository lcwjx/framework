package com.zn.lichen.framework.base;

import android.app.Application;

/**
 * Created by lichen on 2016/12/20.
 */

public class BaseApplication extends Application {

    private static BaseApplication baseApplication;


    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
    }

    public static BaseApplication getAppContext() {
        return baseApplication;
    }
}
