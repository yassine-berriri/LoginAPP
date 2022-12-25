package com.example.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

 public static final String DBNAME="login.db";
    public DBHelper(@Nullable Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("create table users(nom TEXT primary key, prenom TEXT , email TEXT  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
      db.execSQL("drop table if exists users");
    }

    public Boolean insertData(String nom, String prenom,String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("prenom",prenom);
        values.put("nom",nom);
        values.put("email",email);
        long result = db.insert("users",null,values);
        if(result == -1) return false;
        else
            return true;
    }

    public Boolean checkUser(String nom,String prenom,String email){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where nom=? and password=? and email =?",new String[] {nom,prenom,email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

}
