package com.example.project_phase3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowList extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    myDbAdapter helper;
    @SuppressLint("Recycle")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);
        sharedPreferences = this.getSharedPreferences("com.example.project_phase3", MODE_PRIVATE);
        Button Stop = findViewById(R.id.button2);
        ArrayList<String> alarms = new ArrayList<String>();

        String store1= sharedPreferences.getString("Alarm","");
        helper = new myDbAdapter(this);
        long id = helper.insertData(store1);
        if(id<=0)
        {
            Toast.makeText(getApplicationContext(),"Insertion Unsuccessful", Toast.LENGTH_LONG).show();
        } else
        {
            Toast.makeText(getApplicationContext(),"Insertion Successful", Toast.LENGTH_LONG).show();
        }

        alarms = helper.getData();

        ListView listView = findViewById(R.id.listView);
        //alarms.add(store1);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alarms){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.WHITE);
                tv.setTextSize(14);
                return view;
            }
        };
        listView.setAdapter(arrayAdapter);

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}