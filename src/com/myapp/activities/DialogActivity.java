package com.myapp.activities;

import android.app.Activity;
import android.os.Bundle;
import com.example.myapp.R;
import com.myapp.controller.ActivityCollector;

/**
 * Created by megatron on 15/2/18.
 */
public class DialogActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialogactivity);
        ActivityCollector.activityList.add(this);

    }
}