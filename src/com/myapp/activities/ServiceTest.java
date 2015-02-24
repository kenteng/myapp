package com.myapp.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapp.R;
import com.myapp.controller.ActivityCollector;
import com.myapp.objects.MyService;

/**
 * Created by megatron on 15/2/22.
 */
public class ServiceTest extends Activity implements View.OnClickListener{
    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicetest);
        ActivityCollector.activityList.add(this);

        textView = (TextView) findViewById(R.id.textView_servicetest);

        Button bt_progress = (Button) findViewById(R.id.button_progress);
        bt_progress.setOnClickListener(this);

        Button bt_service_start = (Button) findViewById(R.id.button_service_start);
        bt_service_start.setOnClickListener(this);

        Button bt_service_stop = (Button) findViewById(R.id.button_service_stop);
        bt_service_stop.setOnClickListener(this);

    }
    private final int UPDATE_TEXT = 1;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText("HI IT IS ME.");
            }
        }
    };

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.button_progress:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                }).start();
                new DownloadTask().execute();
                break;
            case R.id.button_service_start:
                intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.button_service_stop:
                intent = new Intent(this, MyService.class);
                stopService(intent);
                break;
            default:
                break;
        }
    }

    class DownloadTask extends AsyncTask<Void,Integer,Boolean>{
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute(){
            progressDialog = ProgressDialog.show(ServiceTest.this,"test1","test2");
        }
        @Override
        protected Boolean doInBackground(Void... voids) {
            try{
                int count = 0;
                while(count<=100){
                    publishProgress(count);
                    Thread.sleep(100);
                    count++;
                }
            }catch (Exception ex){
                return false;
            }
            return true;
        }
        @Override
        protected void onProgressUpdate(Integer... values){
            progressDialog.setMessage("Downloaded " + values[0] + "%");
        }

        @Override
        protected void onPostExecute(Boolean result) {
            progressDialog.dismiss();
        }
    }


}
