package com.myapp.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.myapp.R;
import com.myapp.controller.ActivityCollector;
import com.myapp.objects.DBInterface;
import com.myapp.objects.DatabaseHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by megatron on 15/2/21.
 */
public class Database extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.activityList.add(this);
        setContentView(R.layout.database_insert);
        DatabaseHelper databaseHelper = new DatabaseHelper(this,"BookStore.db",null, DBInterface.DATABASE_VERSION);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("sqlite_master",new String[]{"name"},"type='table'",null,null,null,null);
        List<String> tableNames = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                String tableName = cursor.getString(cursor.getColumnIndex("name"));
                //Log.d(this.getClass().getSimpleName(),tableName);
                if(!(tableName.equalsIgnoreCase("android_metadata")) && !(tableName.equalsIgnoreCase("sqlite_sequence")));
                    tableNames.add(tableName);
            }while (cursor.moveToNext());
        }
        cursor.close();
        Spinner spinner = (Spinner) findViewById(R.id.spinner_tableName);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,tableNames);
        spinner.setAdapter(arrayAdapter);

        ContentValues values = new ContentValues();
        values.put("name","First Love");
        values.put("price","10.0");
        sqLiteDatabase.insert("book",null,values);
    }
}