package com.myapp.objects;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * Created by megatron on 15/2/21.
 */
public class MyProvider extends ContentProvider{
    //常量表示如果匹配路径时的返回数值
    private static final int BOOK_DIR = 0;
    private static final int BOOK_ITEM = 1;
    private UriMatcher uriMatcher;
    private DatabaseHelper databaseHelper;
    private String AUTHORITY = "com.example.myapp.provider";
    public MyProvider(){
        //常量UriMatcher.NO_MATCH表示不匹配任何路径的返回码
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_DIR);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
    }
    @Override
    public boolean onCreate() {
        databaseHelper = new DatabaseHelper(getContext(),"BookStore.db",null, DBInterface.DATABASE_VERSION);
        return false;
    }

    /*
    * query()
    * 方法参数     对应SQL部分                  描述
    * uri         from table_name             指定查询某个应用程序下的某一张表
    * projection  select column1, column2     指定查询的列名
    * selection   where column = value        指定 where 的约束条件
    *selectionArgs   -                        为 where 中的占位符提供具体的值
    *orderBy      order by column1, column2   指定查询结果的排序方式
    * */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                Log.d(this.getClass().getSimpleName(),"Require book dir.");
                /*maybe wrong, the order of arguments. For here, I do not care the order of arguments since
                I am not going to use them*/
                cursor = db.query("book",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                Log.d("",uri.getPathSegments().get(0));
                String bookId = uri.getPathSegments().get(1);
                Log.d(this.getClass().getSimpleName(),"Require book items.");
                cursor = db.query("book",projection,"id=?",new String[]{bookId},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.myapp.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.myapp.provider.book";
            default:
                break;
        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        long bookId = db.insert("book",null,contentValues);
        return Uri.parse("content://"+AUTHORITY+"/book/"+bookId);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
    /*
    * Issue 1:
    * The test application(another and new one) could throw the exception like
    * "java.lang.SecurityException: Permission Denial: opening provider...",
    * the reason is the application which provide the provider didn't export the provider.
    * Add "android:exported = "true" within the <provider> section could solve this issue.
    *
    *
    * */
}
