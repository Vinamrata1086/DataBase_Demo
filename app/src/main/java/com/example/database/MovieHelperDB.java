package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MovieHelperDB extends SQLiteOpenHelper {
    public static final String DB_NAME ="moviedb";
    public static final  int version=1;
    Context c;
    String query="create table if not exists "+Movie.Data.Table_Name+" ("+" "+Movie.Data.M_ID+" integer primary key autoincrement ,"+ Movie.Data.M_Name+" varchar , "+Movie.Data.M_TYPE+" varchar , "+ Movie.Data.M_ACTOR+" varchar , "+Movie.Data.M_ACTRESS+" varchar , "+Movie.Data.M_RYEAR+" varchar )";
    public MovieHelperDB(@Nullable Context context) {
        super(context, DB_NAME, null, version);
        c=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query);
        Toast.makeText(c, "DataBase Created....!", Toast.LENGTH_SHORT).show();
    }
    public void addRecord(String name,String actor,String actress,String type,String ryear,SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(Movie.Data.M_Name,name);
        cv.put(Movie.Data.M_TYPE,type);
        cv.put(Movie.Data.M_ACTOR,actor);
        cv.put(Movie.Data.M_ACTRESS,actress);
        cv.put(Movie.Data.M_RYEAR,ryear);
        db.insert(Movie.Data.Table_Name,null,cv);
        Toast.makeText(c, "Data Added", Toast.LENGTH_SHORT).show();
    }
    public Cursor getRecord(SQLiteDatabase db){
        Cursor c;
        String []col={Movie.Data.M_ID,Movie.Data.M_Name,Movie.Data.M_TYPE,Movie.Data.M_ACTOR,Movie.Data.M_ACTRESS,Movie.Data.M_RYEAR};
        c=db.query(Movie.Data.Table_Name,col,null,null,null,null,null);
        return c;
    }
    public void delete(String id,SQLiteDatabase db)
    {
        db.execSQL("delete from "+Movie.Data.Table_Name+" where "+Movie.Data.M_ID+"='"+id+"'");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

