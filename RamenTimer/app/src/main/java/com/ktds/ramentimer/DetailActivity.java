package com.ktds.ramentimer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViewsService;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    private TextView tv_min;
    private TextView tv_sec;
    private Button btn_play;
    private Button btn_stop;

    private Handler handler;

    private IMyTimerlInterface binder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = IMyTimerlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        getSupportActionBar().setTitle(intent.getStringExtra("Name"));
        setContentView(R.layout.activity_detail);

        handler = new Handler();

        final int time = intent.getIntExtra("TIME",0);

        tv_min = (TextView) findViewById(R.id.tv_min);
        tv_sec = (TextView) findViewById(R.id.tv_sec);
        btn_play = (Button) findViewById(R.id.btn_play);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(DetailActivity.this, RamenTimerService.class);
                detailIntent.putExtra("TIME",time);
                bindService(detailIntent, connection, BIND_AUTO_CREATE);

                Thread thread = new Thread(new GetTimerThread());
                thread.start();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });

    }
    class GetTimerThread implements Runnable {
        int min = 0;
        int sec = 0;
        @Override
        public void run() {

            while (true) {
                if ( binder == null ){
                    continue;
                }
                try {
                    min = binder.getMin();
                    sec = binder.getSec();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv_min.setText(min + "분");
                        tv_sec.setText(sec + "초");

                    }
                });
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
