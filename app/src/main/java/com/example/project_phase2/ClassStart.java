package com.example.project_phase2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;


public class ClassStart extends BroadcastReceiver {

    @Override
    public void onReceive(Context c, Intent intent) {

        AudioManager audioManager =  (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);;
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);

    }

}
