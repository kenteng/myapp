package com.myapp.controller;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by megatron on 15/2/20.
 */
public class ActivityCollector {
    public static List<Activity> activityList = new ArrayList<>();

    public static void finishAll(){
        for(Activity activity:activityList)
            activity.finish();
    }
}
