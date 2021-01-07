package com.example.project_phase2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import java.util.Calendar;

public class InputTime extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button setAlarm;
    TimePicker timePicker;
    String day_Selected;
    String store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_time);

        init();
        setAlarm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ToastMessage();
                AlarmSet();
                Intent intent = new Intent(InputTime.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void ToastMessage() {
        if (Build.VERSION.SDK_INT >= 23) {
            Toast.makeText(getApplicationContext(), "Alarm set for day " + day_Selected +
                            " at time " + timePicker.getHour() + " hours and "
                            + timePicker.getMinute()+ " minutes",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Alarm set for day " + day_Selected +
                            " at time " + timePicker.getCurrentHour() + " hours and "
                            + timePicker.getCurrentMinute() + " minutes",
                    Toast.LENGTH_LONG).show();
        }
        store ="Alarm set for day " + day_Selected +
                " at time " + timePicker.getHour() + " hours and "
                + timePicker.getMinute()+ " minutes";
        sharedPreferences.edit().putString("Alarm",store).apply();

    }

    private void init() {
        timePicker = findViewById(R.id.timepicker);
        setAlarm = findViewById(R.id.next1);
        sharedPreferences = this.getSharedPreferences("com.example.project_phase2", MODE_PRIVATE);
        day_Selected = sharedPreferences.getString("Send_day","");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void AlarmSet() {
        Calendar calendar = Calendar.getInstance();

        if (Build.VERSION.SDK_INT >= 23) {
            calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getHour(),
                    timePicker.getMinute(),
                    0
            );
        }else
        {
            calendar.set(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    timePicker.getCurrentHour(),
                    timePicker.getCurrentMinute(),
                    0);
        }
            SetAlarm(calendar.getTimeInMillis());

    }

    @SuppressLint("ShortAlarm")
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void SetAlarm(long timeInMillis) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent =  new Intent(this, ClassStart.class);
            PendingIntent pendingIntent =  PendingIntent.getBroadcast(this,0,intent,0);
            alarmManager.setRepeating(AlarmManager.RTC, timeInMillis, 1000,pendingIntent);
    }
}


