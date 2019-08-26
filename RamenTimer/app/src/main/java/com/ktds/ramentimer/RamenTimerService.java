package com.ktds.ramentimer;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.Locale;


public class RamenTimerService extends Service {

    private Handler handler;
    private TextToSpeech tts;
    private int min;
    private int sec;
    private int time;

    private IMyTimerlInterface.Stub binder = new IMyTimerlInterface.Stub() {
        @Override
        public int getMin() throws RemoteException {
            return min;
        }

        @Override
        public int getSec() throws RemoteException {
            return sec;
        }
    };

    public RamenTimerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        handler = new Handler();
        min = 0;
        sec = 0;
        time = 0;

        Thread timer = new Thread(new TimerThread());
        timer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {

        time = intent.getIntExtra("TIME",0);
        return binder;
    }

    public boolean onUnbind(Intent intent) {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = 0;
        return true;
    }
    class TimerThread implements Runnable {
        @Override
        public void run() {
            for ( ; time >= 0 ; time-- ){
                min = time / 60;
                sec = time % 60;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) { }
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            tts.setLanguage(Locale.US);
                            tts.setSpeechRate(1.0f);
                            tts.speak("Enjoy", TextToSpeech.QUEUE_FLUSH, null);
                        }
                    });

                }
            });
        }

    }
}
