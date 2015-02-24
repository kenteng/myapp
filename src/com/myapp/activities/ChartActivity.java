package com.myapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.myapp.R;
import com.myapp.controller.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by megatron on 15/2/19.
 */
public class ChartActivity extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chart);
        ActivityCollector.activityList.add(this);

        final List<String> msgList = new ArrayList<>();

        ListView listView = (ListView) findViewById(R.id.msg_list_view);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,msgList);
        listView.setAdapter(arrayAdapter);

        final EditText editText = (EditText) findViewById(R.id.editText_edit);

        Button btSend = (Button) findViewById(R.id.button_send);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = editText.getText().toString();
                if (!"".equals(inputString)) {
                    arrayAdapter.add(inputString);
                    editText.setText("");
                }
                /*msgList.add(inputString);
                arrayAdapter.notifyDataSetChanged();*/
            }
        });
    }

}
