package com.example.project_phase2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button start;
    Button Stop;
    Button show;
    ArrayList<String> All_Days;
    SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.project_phase2", MODE_PRIVATE);;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        Listeners();
    }

    private void Listeners() {
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Choose_Day.class);
                startActivity(intent);
            }
        });


        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    All_Days = (ArrayList<String>) ObjectSerialize.deserialize(sharedPreferences.getString("Alarm", ObjectSerialize.serialize(new ArrayList<String>())));
                    } catch ( IOException e) {
                     e.printStackTrace();
                    }
                    Alert(All_Days.toString());
            }
        });



        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            System.exit(0);
            }
        });
    }


    private void Alert(String s) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage(s);
        builder.setTitle("All Alarms are: ");
        builder.setIcon(R.drawable.image1);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"OK!!",Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


    private void init() {
    start= findViewById(R.id.next1);
    Stop = findViewById(R.id.button2);
    show = findViewById(R.id.show);
    }
}
