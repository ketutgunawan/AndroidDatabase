package com.droidsergeant.androiddatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper {

    String DATABASE_NAME = "database.db";
    String TABLE_NAME = "user_detail";
    InnerHelper helper;
    SQLiteDatabase database;

    public DatabaseHelper(Context context){
        helper = new InnerHelper(context, DATABASE_NAME, null, 1);
    }

    //Database should opened first to insert values.
    public DatabaseHelper open(){
        database = helper.getWritableDatabase();
        return this;
    }

    //Used to insert database. First parameter indicates the table name and second for content values.
    public long insertData(String table, ContentValues values){
        return database.insert(table,null, values);
    }

    //Getting data from the table in the form of cursor
    public Cursor getData(String table_name){
        return database.query(table_name, null, null, null, null, null, null);
    }

    //After adding values database must be closed.
    public void close(){
        database.close();
    }

    //InnerClass to create and upgrade database
    public class InnerHelper extends SQLiteOpenHelper {

        public InnerHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        //A table is created with a table name and brackets contain the column names.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE 'user_detail'(user_name string);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
