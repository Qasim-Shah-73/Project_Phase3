package com.example.project_phase2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Button start;
    Button Stop;
    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.example.project_phase2", MODE_PRIVATE);
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
                String store= sharedPreferences.getString("Alarm","");
                Alert(store);
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
        builder.setTitle("Recent Alarms is: ");
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
