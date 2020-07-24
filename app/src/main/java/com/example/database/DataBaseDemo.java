package com.example.database;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DataBaseDemo extends AppCompatActivity {
    MovieHelperDB mhd;
    SQLiteDatabase db;
    Button reg;
    ListView lv;
    MovieCursorAdaptor mca;
    ArrayList<String> id = new ArrayList<String>();
    View.OnClickListener ocl = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final Dialog d= new Dialog(DataBaseDemo.this);
            d.setContentView(R.layout.registration);
            d.show();
            final EditText name=d.findViewById(R.id.mname);
            final EditText type=d.findViewById(R.id.mtype);
            final EditText actor=d.findViewById(R.id.mactor);
            final EditText actress=d.findViewById(R.id.mactress);
            final EditText year=d.findViewById(R.id.mryear);
            Button add=d.findViewById(R.id.register);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mname = name.getText().toString();
                    String mtype = type.getText().toString();
                    String mactor = actor.getText().toString();
                    String mactress = actress.getText().toString();
                    String mryear = year.getText().toString();
                    mhd.addRecord(mname,mactor,mactress,mtype,mryear,db);
                    Toast.makeText(DataBaseDemo.this, "Data Added....!!!!", Toast.LENGTH_SHORT).show();
                    d.dismiss();
                    getData();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base_demo);
        mhd=new MovieHelperDB(this);
        db=mhd.getWritableDatabase();
        reg=findViewById(R.id.addmovie);
        reg.setOnClickListener(ocl);
        lv=findViewById(R.id.mlist);
        getData();
        registerForContextMenu(lv);
    }
    void getData(){
        Cursor c= mhd.getRecord(db);
        mca=new MovieCursorAdaptor(this,c,0);
        lv.setAdapter(mca);
        Cursor c1=mhd.getRecord(db);
        while(c1.moveToNext()){
            id.add(c1.getString(c1.getColumnIndex(Movie.Data.M_ID)));

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Delete Record");
        menu.add(0,v.getId(),0,"Delete");
        menu.add(0,v.getId(),0,"Update");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getTitle().toString().equals("Delete")) ;
        {
            AdapterView.AdapterContextMenuInfo acm = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            mhd.delete(id.get(acm.position), db);
            getData();
        }
        return false;

    }
}
