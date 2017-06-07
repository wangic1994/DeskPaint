package com.example.wangicclock;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import c.JniClient;
import db.DbHelper;


public class MainActivity extends Activity {
    DbHelper dbHelper;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JniClient jniClient = new JniClient();
        Log.e(TAG, "onCreate: "+ jniClient.getStr());

        dbHelper = new DbHelper(this);
        SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","wangic1");
        contentValues.put("age",2);
        writableDatabase.insert("wangic",null,contentValues);
        contentValues = new ContentValues();
        contentValues.put("name","wangic2");
        writableDatabase.update("wangic",contentValues,"name = ?",new String[]{"wangic"});
//        writableDatabase.delete("wangic","name = ?",new String[]{"wangic2"});
        writableDatabase.close();
        SQLiteDatabase readableDatabase = dbHelper.getReadableDatabase();
        Cursor wangic = readableDatabase.query("wangic", new String[]{"personid","name","age"}, "age = ? and name = ?", new String[]{"1","wangic1"}, null, null,"personid ASC");
        while(wangic.moveToNext()){
            String personid = wangic.getString(wangic.getColumnIndex("personid"));
            String name = wangic.getString(wangic.getColumnIndex("name"));
            String age = wangic.getString(wangic.getColumnIndex("age"));
            Log.e(TAG, "onCreate: "+personid+name+age);
        }
        readableDatabase.close();


        this.finish();
    }
}
