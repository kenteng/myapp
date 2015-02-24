package com.myapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import com.example.myapp.R;
import com.myapp.adapter.MsgAdapter;
import com.myapp.controller.ActivityCollector;
import com.myapp.objects.Msg;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by megatron on 15/2/20.
 */
public class ComplicatedChart extends Activity{
    private List<Msg> msgList = new ArrayList<Msg>();
    private static boolean flip = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.chart);
        ActivityCollector.activityList.add(this);

        final ListView listView = (ListView) findViewById(R.id.msg_list_view);
        final MsgAdapter msgAdapter = new MsgAdapter(this,R.layout.msg_item,msgList);
        listView.setAdapter(msgAdapter);
        load();
        listView.setSelection(msgAdapter.getCount());

        final EditText editText = (EditText) findViewById(R.id.editText_edit);

        Button btSend = (Button) findViewById(R.id.button_send);
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputString = editText.getText().toString();
                if(!"".equals(inputString)){
                    Msg msg = new Msg(inputString,flip);
                    flip = !flip;
                    msgList.add(msg);
                    msgAdapter.notifyDataSetChanged();
                    editText.setText("");
                    listView.setSelection(msgAdapter.getCount());
                }
            }
        });

        Button btClear = (Button) findViewById(R.id.button_clear);
        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msgAdapter.clear();
                File dataFile = new File("/data/data/com.example.myapp/files/chartData");
                if(dataFile.exists()){
                    Log.i(this.getClass().getSimpleName(),"Delete data file.");
                    dataFile.delete();
                }
                else
                    Log.i(this.getClass().getSimpleName(),"Data file does not exist.");
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        save();
        Log.d(this.getClass().getSimpleName(),"Activity is destroyed.");
    }
    private void load(){
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            File dataFile = new File("/data/data/com.example.myapp/files/chartData");
            if(!dataFile.exists()){
                Log.d(this.getClass().getSimpleName(),"Data file "+dataFile.getAbsolutePath()+" does not exist.");
                return;
            }
            fileInputStream = openFileInput("chartData");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line;
            while((line=bufferedReader.readLine())!=null){
                Msg msg;
                String str = line.split("isSend:")[0];
                String sender = line.split("isSend:")[1];
                if("1".equals(sender))
                    msg = new Msg(str,true);
                else
                    msg = new Msg(str,false);
                msgList.add(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void save(){
        FileOutputStream out = null;
        BufferedWriter bufferedWriter = null;
        try {
            out = openFileOutput("chartData",MODE_APPEND);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));
            for(Msg msg:msgList){
                String isSend = "0";
                if(msg.isSend())
                    isSend = "1";
                bufferedWriter.write(msg.getMsg()+" isSend:"+isSend+"\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(bufferedWriter!=null)
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
