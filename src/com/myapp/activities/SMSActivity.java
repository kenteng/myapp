package com.myapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.myapp.R;
import com.myapp.controller.ActivityCollector;

/**
 * Created by megatron on 15/2/22.
 */
public class SMSActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms);
        ActivityCollector.activityList.add(this);
        ListView listView = (ListView) findViewById(R.id.listView_sms);
        Intent intent = getIntent();

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
    }
}
