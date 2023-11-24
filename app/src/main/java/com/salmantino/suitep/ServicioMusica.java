package com.salmantino.suitep;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;


public class ServicioMusica extends Service {

    MediaPlayer reproductor;
    final int[] audio = {
        R.raw.tu_madre_tiene, R.raw.freddy_fazbear,
        R.raw.mcdonal_dona, R.raw.king_ohio,
        R.raw.bluetooth_connected, R.raw.bluetooth_pair,
        R.raw.goofy_ahh_android, R.raw.goofy_ahh_piano,
    };

    public void onCreate() {
        super.onCreate();

        int rnd = (int) Math.ceil(Math.random() * 8);
        reproductor = MediaPlayer.create(this, audio[rnd - 1]);
        reproductor.setLooping(true);
        reproductor.setVolume(50, 50);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        reproductor.start();
        return START_NOT_STICKY;
    }


    public void onDestroy() {
        super.onDestroy();

        if (reproductor.isPlaying()) {
            reproductor.stop();
            reproductor.release();
            reproductor = null;
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
