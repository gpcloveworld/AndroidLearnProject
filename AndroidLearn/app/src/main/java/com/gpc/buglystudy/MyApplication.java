package com.gpc.buglystudy;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), AppConstants.BUGLY_APPID, !AppConstants.isRelease);
    }
}
