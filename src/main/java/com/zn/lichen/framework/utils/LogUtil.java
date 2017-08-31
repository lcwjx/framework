package com.zn.lichen.framework.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.zn.lichen.framework.base.BaseApplication;

import java.lang.reflect.Field;

/**
 * Created by lichen on 2017/1/5.
 */

public class LogUtil {
    private static final String TAG = "APP_LOG";

    public static int LogLevel = Log.DEBUG;

    static {
        //作为一个library的module对外总是提供release版，导致BuildConfig.DEBUG总是false。
        //所以此处使用反射获取app项目中的debug标志位。
        String processName = getCurProcessName(BaseApplication.getAppContext());
        try {
            String packageName = processName.split(":")[0];
            if (packageName.contains("siapp")) {
                packageName = "com.paic.mhis.siapp";
            } else if (packageName.contains("doctor")) {
                packageName = "com.paic.mhis.doctor";
            } else {
                packageName = "com.paic.mhis.ncms";
            }
            final Class<?> buildConfig = Class.forName(packageName + ".BuildConfig");
            final Field debug = buildConfig.getField("DEBUG");
            debug.setAccessible(true);
            boolean isDebug = debug.getBoolean(null);
            LogUtil.LogLevel = isDebug ? Log.DEBUG : Log.ERROR;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


    public static void e(String msg) {
        e(TAG, msg);
    }

    public static void e(String tag, String msg) {
        Log.e(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (Log.INFO >= LogLevel) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void d(String tag, String msg) {
        if (Log.DEBUG >= LogLevel) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

}
