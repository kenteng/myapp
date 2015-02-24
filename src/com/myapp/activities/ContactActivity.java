package com.myapp.activities;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.myapp.R;
import com.myapp.controller.ActivityCollector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by megatron on 15/2/21.
 */
public class ContactActivity extends Activity {

    private List<String> contactList = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);
        ActivityCollector.activityList.add(this);
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactList.add(name + " : " +phoneNumber);
        }
        Log.d(this.getClass().getSimpleName(),contactList.toString());
        ListView listView = (ListView) findViewById(R.id.listView_contact);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,contactList);
        listView.setAdapter(arrayAdapter);
    }
}
