package com.myapp.objects;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by megatron on 15/2/22.
 */
public class MyService extends Service{
    private DownloadBinder binder = new DownloadBinder();
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("MyService", "onCreate executed.");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "MyService starts.");
        return super.onStartCommand(intent, flags, startId);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "MyService is destroyed.");
    }

    class DownloadBinder extends Binder{
        public void startDownload(){
            Log.d("MyService", "startDownload executed");
        }
        public void stopDownload(){
            Log.d("MyService", "stopDownload executed");
        }
    }
}
