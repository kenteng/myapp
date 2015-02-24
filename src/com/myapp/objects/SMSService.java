package com.myapp.objects;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import com.example.myapp.R;
import com.myapp.controller.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by megatron on 15/2/22.
 */
public class SMSService extends BroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        SmsMessage[] smsMessages = new SmsMessage[pdus.length];
        ArrayList<String> msgList = new ArrayList<>();
        for(int i=0;i<pdus.length;i++){
            smsMessages[i]=SmsMessage.createFromPdu((byte[]) pdus[i]);
            String sender = smsMessages[i].getOriginatingAddress();
            String content = smsMessages[i].getDisplayMessageBody();
            msgList.add(sender+":"+content);
        }
        Intent outIntent = new Intent();
        outIntent.putStringArrayListExtra("msg", msgList);

    }
}
