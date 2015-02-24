package com.myapp.objects;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by megatron on 15/2/21.
 */
public class DatabaseHelper extends SQLiteOpenHelper{
    private final String createBook = "CREATE TABLE book(" +
                                        "id integer primary key autoincrement," +
                                        "author text," +
                                        "price real," +
                                        "pages integer," +
                                        "name text)";
    private final String createCategory = "create table Category ("+
                                        "id integer primary key autoincrement,"+
                                        "category_name text,"+
                                        "category_code integer)";
    private final String createSales = "CREATE TABLE sales(id integer primary key autoincrement,name text,price real)";

    private Context context;
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createBook);
        Log.d(this.getClass().getSimpleName(),"created table book");
        sqLiteDatabase.execSQL(createCategory);
        Log.d(this.getClass().getSimpleName(),"created table category");
        sqLiteDatabase.execSQL(createSales);
        Log.d(this.getClass().getSimpleName(),"created table sales");
        Toast.makeText(context,"Create(Update) database is done",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Book");
        sqLiteDatabase.execSQL("drop table if exists Category");
        sqLiteDatabase.execSQL("drop table if exists sales");
        onCreate(sqLiteDatabase);
    }
}
