package com.myapp.objects;

import android.app.Application;
import android.content.Context;

/**
 * Created by megatron on 15/2/23.
 * 这里的Context为全局的Context，注册全局的context 需要在AndroidManifest中注册
 */
public class MyApplication extends Application{
    private static Context context;
    @Override
    public void onCreate(){
        context = getApplicationContext();
    }
    public static Context getContext(){return context;}
}
