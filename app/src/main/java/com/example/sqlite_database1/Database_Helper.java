package com.example.sqlite_database1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLPermission;

public class Database_Helper extends SQLiteOpenHelper {

    private static String Database_Name = "User.db";
    private static String Table_Name = "User";
    public static String Col_ID = "Id";
    public static String Col_Name = "Name";
    public static String Col_Age = "Age";
    private static int Version = 1;

    private String Create_Table ="create table "+Table_Name+" ("+Col_ID+" Integer primary key autoincrement,"+Col_Name+" Text, "+Col_Age+" Text)";



    public Database_Helper(@Nullable Context context) {
        super(context,Database_Name , null, Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertData(String name, String age){

        ContentValues contentValues = new ContentValues();

        contentValues.put(Col_Name,name);
        contentValues.put(Col_Age,age);

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Long id = sqLiteDatabase.insert(Table_Name,null,contentValues);
        sqLiteDatabase.close();

        return id;

    }

    public Cursor showData(){

        String All_Data_Query = " Select * From " +Table_Name;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(All_Data_Query,null);
        return cursor;


    }

    public Cursor searchData(int ID) {

        String Search_SQL = "select * From "+Table_Name+" where "+Col_ID+"="+ID;

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(Search_SQL,null);

        return cursor;

    }

    public boolean updateData(int id,String name, String age){

        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_ID,id);
        contentValues.put(Col_Name,name);
        contentValues.put(Col_Age,age);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update(Table_Name,contentValues,Col_ID+" = ? ",new String[]{String.valueOf(id)});

        return true;


    }
    public int deleteData(int id){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        int check = sqLiteDatabase.delete(Table_Name,Col_ID+" =? ",new String[]{String.valueOf(id)});
        return check;


    }


}
