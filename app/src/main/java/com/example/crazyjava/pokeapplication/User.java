package com.example.crazyjava.pokeapplication;

/**
 * Created by CrazyJava on 16/11/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class User extends SQLiteOpenHelper
{
    String creacion = "CREATE TABLE User (Name TEXT, Pass TEXT)";
    public User(Context context, String name, CursorFactory factory,int version)
    {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase bbdd)
    {
// TODO Auto-generated method stub
        bbdd.execSQL(creacion);
    }
    @Override
    public void onUpgrade(SQLiteDatabase bbdd, int versionantigua, int versionnueva) {
// TODO Auto-generated method stub
        bbdd.execSQL("DROP TABLE IF EXISTS User ");
        bbdd.execSQL(creacion);
    }
}
