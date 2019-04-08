package com.gpc.buglystudy.base.utils;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class ExitAppUtils {
    private final List<Activity> mActivityList = new LinkedList<>();
    private static ExitAppUtils instance = new ExitAppUtils();

    private ExitAppUtils() {
    }

    public static ExitAppUtils getInstance() {
        return instance;
    }

    public void addActivity(Activity activity) {
        mActivityList.add(activity);
    }

    public void delActivity(Activity activity) {
        mActivityList.remove(activity);
    }

    public void exit() {
        for (Activity activity : mActivityList
                ) {
            activity.finish();

        }
        System.exit(0);
    }
}
