package com.example.database;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

class MovieCursorAdaptor extends CursorAdapter {

    public MovieCursorAdaptor(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.mylistviewdesign,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView name=view.findViewById(R.id.mname);
        TextView actor=view.findViewById(R.id.mactor);
        TextView actress=view.findViewById(R.id.mactress);
        TextView type=view.findViewById(R.id.type);
        TextView year=view.findViewById(R.id.year);
        String mname=cursor.getString(cursor.getColumnIndexOrThrow(Movie.Data.M_Name));
        String mactor=cursor.getString(cursor.getColumnIndexOrThrow(Movie.Data.M_ACTOR));
        String mactress=cursor.getString(cursor.getColumnIndexOrThrow(Movie.Data.M_ACTRESS));
        String mtype=cursor.getString(cursor.getColumnIndexOrThrow(Movie.Data.M_TYPE));
        String ryear=cursor.getString(cursor.getColumnIndexOrThrow(Movie.Data.M_RYEAR));
        name.setText(mname);
        actor.setText(mactor);
        actress.setText(mactress);
        type.setText(mtype);
        year.setText(ryear);
    }
}