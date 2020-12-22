package com.example.project_phase2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class Choose_Day extends AppCompatActivity {
    Button next1;
    RadioButton r1,r2,r3,r4,r5,r6,r7;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__day);

        init();

        next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckDay();
                Intent intent = new Intent(Choose_Day.this,InputTime.class);
                startActivity(intent);
            }
        });

    }

    private void CheckDay()
    {
        if (r1.isChecked())
        {
            sharedPreferences.edit().putString("Send_day","Monday").apply();
        }
        if (r2.isChecked())
        {
            sharedPreferences.edit().putString("Send_day","Tuesday").apply();
        }
        if (r3.isChecked())
        {
            sharedPreferences.edit().putString("Send_day","Wedneday").apply();
        }
        if (r4.isChecked())
        {
            sharedPreferences.edit().putString("Send_day","Thursaday").apply();
        }
        if (r5.isChecked())
        {
            sharedPreferences.edit().putString("Send_day","Friday").apply();
        }
        if (r6.isChecked())
        {
            sharedPreferences.edit().putString("Send_day","Saturday").apply();
        }
        if (r7.isChecked())
        {
            sharedPreferences.edit().putString("Send_day","Sunday").apply();
        }

    }
    private void init() {
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        r1 = findViewById(R.id.Monday);
        r2 = findViewById(R.id.Tuesday);
        r3 = findViewById(R.id.Wednesday);
        r4 = findViewById(R.id.Thursday);
        r5 = findViewById(R.id.Friday);
        r6 = findViewById(R.id.Saturday);
        r7 = findViewById(R.id.Sunday);
        next1 = findViewById(R.id.next1);
        sharedPreferences =this.getSharedPreferences("com.example.project_phase2", MODE_PRIVATE);
    }
}