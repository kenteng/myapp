package com.myapp.activities;

import android.app.*;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.myapp.R;
import com.myapp.broadcast.NetworkChangeReceiver;
import com.myapp.controller.ActivityCollector;
import com.myapp.objects.DBInterface;
import com.myapp.objects.DatabaseHelper;

import java.io.File;

public class MyActivity extends Activity implements View.OnClickListener{
    private NetworkChangeReceiver networkChangeReceiver;
    private IntentFilter intentFilter;
    private boolean networkBroadcastEnabled = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //hide the title of application while it starts
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        ActivityCollector.activityList.add(this);
        //如果savedInstanceState非NULL，即当前Activity是重新Create的，并且有保存的状态。
        if(savedInstanceState!=null){
            //
        }
        if(networkBroadcastEnabled){
            intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            networkChangeReceiver = new NetworkChangeReceiver();
            registerReceiver(networkChangeReceiver,intentFilter);
        }


        Button bt = (Button) findViewById(R.id.button_1);
        bt.setOnClickListener(this);

        Button bt_normal = (Button) findViewById(R.id.button_normal);
        bt_normal.setOnClickListener(this);

        Button bt_dialog = (Button) findViewById(R.id.button_dialog);
        bt_dialog.setOnClickListener(this);

        Button bt_alertDialog = (Button) findViewById(R.id.button_alertDialog);
        bt_alertDialog.setOnClickListener(this);

        Button bt_progressDialog = (Button) findViewById(R.id.button_progressDialog);
        bt_progressDialog.setOnClickListener(this);

        Button bt_chart = (Button) findViewById(R.id.button_chart);
        bt_chart.setOnClickListener(this);

        Button bt_complicatedChart = (Button) findViewById(R.id.button_complicatedChart);
        bt_complicatedChart.setOnClickListener(this);

        Button bt_forceQuit = (Button) findViewById(R.id.button_forceQuit);
        bt_forceQuit.setOnClickListener(this);

        Button bt_logout = (Button) findViewById(R.id.button_logout);
        bt_logout.setOnClickListener(this);

        Button bt_dbExample = (Button) findViewById(R.id.button_dbExample);
        bt_dbExample.setOnClickListener(this);

        Button bt_dbInsert = (Button) findViewById(R.id.button_insert);
        bt_dbInsert.setOnClickListener(this);

        Button bt_dbReplace = (Button) findViewById(R.id.button_replace);
        bt_dbReplace.setOnClickListener(this);

        Button bt_contact = (Button) findViewById(R.id.button_contact);
        bt_contact.setOnClickListener(this);

        Button bt_notify = (Button) findViewById(R.id.button_notify);
        bt_notify.setOnClickListener(this);

        Button bt_service = (Button) findViewById(R.id.button_service);
        bt_service.setOnClickListener(this);

        Button bt_webview = (Button) findViewById(R.id.button_webview);
        bt_webview.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key", tempData);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(this.getClass().getName(),"onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(this.getClass().getName(),"onResume");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(this.getClass().getName(),"onStop");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(this.getClass().getName(),"onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(networkBroadcastEnabled)
            unregisterReceiver(networkChangeReceiver);
        Log.d(this.getClass().getName(), "onDestroy");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(this.getClass().getName(), "onRestart");
    }

    //添加菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_myactivity,menu);
        return true;
    }
    //设定Menu点击后的效果
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.add:
                Log.d(this.getClass().getName(),"Click add item");
                Toast.makeText(MyActivity.this, "add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove:
                Toast.makeText(MyActivity.this,"remove",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.button_1:
                intent = new Intent(MyActivity.this,SecActivity.class);
                intent.putExtra("name", "kteng");
                startActivity(intent);
                break;
            case R.id.button_normal:
                intent = new Intent(MyActivity.this,NormalActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_dialog:
                intent = new Intent(MyActivity.this,DialogActivity.class);
                startActivity(intent);
                break;
            case R.id.button_alertDialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Test dialog");
                builder.setMessage("something important");
                builder.setCancelable(true);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
                break;
            case R.id.button_progressDialog:
                ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("This is a progress dialog.");
                progressDialog.setMessage("Loading....");
                progressDialog.setCancelable(true);
                progressDialog.setProgressStyle(ProgressDialog.BUTTON_NEGATIVE);
                progressDialog.show();
                break;
            case R.id.button_chart:
                intent = new Intent(this,ChartActivity.class);
                startActivity(intent);
                break;
            case R.id.button_complicatedChart:
                intent = new Intent(this,ComplicatedChart.class);
                startActivity(intent);
                break;
            case R.id.button_forceQuit:
                intent = new Intent("com.myapp.broadcast.quit");
                sendBroadcast(intent);
                break;
            case R.id.button_logout:
                SharedPreferences.Editor editor = getSharedPreferences("userProfile",MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();
                intent = new Intent(this,LoginActivity.class);
                ActivityCollector.finishAll();
                startActivity(intent);
                break;
            case R.id.button_dbExample:
                DatabaseHelper databaseHelper = new DatabaseHelper(this,"BookStore.db",null,DBInterface.DATABASE_VERSION);
                databaseHelper.getWritableDatabase();
                break;
            case R.id.button_insert:
                intent = new Intent(this,Database.class);
                startActivity(intent);
                break;
            case R.id.button_replace:
                replace();
                break;
            case R.id.button_contact:
                intent = new Intent(this,ContactActivity.class);
                startActivity(intent);
                break;
            case R.id.button_notify:
                notifyPractice();
                break;
            case R.id.button_service:
                intent = new Intent(this,ServiceTest.class);
                startActivity(intent);
                break;
            case R.id.button_webview:
                intent = new Intent(this,WebViewActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }

    }
    private void notifyPractice(){
        Intent intent = new Intent(this,NotifyActivity.class);
        PendingIntent pi = PendingIntent.getActivities(this,0,new Intent[]{intent},PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(R.drawable.ic_launcher,"test notification",System.currentTimeMillis());
        notification.setLatestEventInfo(this, "This is content title", "This is content text", pi);
        Uri soundUri = Uri.fromFile(new File("/system/media/audio/ringtones/Basic_tone.ogg"));
        notification.sound = soundUri;
        long[] vibrates = {0, 1000, 1000, 1000};
        notification.vibrate = vibrates;
        manager.notify(1,notification);
    }
    private void replace(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this,"BookStore.db",null, DBInterface.DATABASE_VERSION);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        db.beginTransaction();
        //get data
        String queryStr = "select name,price from book";
        Cursor cursor = db.rawQuery(queryStr, null);
        if(!cursor.moveToFirst())
            return;
        String name = cursor.getString(cursor.getColumnIndex("name"));
        float price = cursor.getFloat(cursor.getColumnIndex("price"));
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        cursor.close();

        //remove from book table
        db.delete("book","id="+id,null);

        //insert into sales table
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("price",price);
        db.insert("sales",null,values);
        db.endTransaction();
    }
}
