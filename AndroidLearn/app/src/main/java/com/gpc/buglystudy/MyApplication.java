package com.gpc.buglystudy;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //bugly初始化
        CrashReport.initCrashReport(getApplicationContext(), AppConstants.BUGLY_APPID, !AppConstants.isRelease);
    }
}
