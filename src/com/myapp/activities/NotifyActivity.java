package com.myapp.activities;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import com.example.myapp.R;
import com.myapp.controller.ActivityCollector;

/**
 * Created by megatron on 15/2/22.
 */
public class NotifyActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notify);
        ActivityCollector.activityList.add(this);
        //打开Activity的时候取消提醒图标
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //1，notification的ID
        manager.cancel(1);
    }
}
