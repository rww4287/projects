package com.ktds.jobs;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Handler handler;
    private TextView tv_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();
        tv_title = (TextView) findViewById(R.id.tv_title);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "Aleo-Bold.otf");
        tv_title.setTypeface(typeFace);
        handler.postDelayed(mrun, 2000);
    }

    Runnable mrun = new Runnable(){
        @Override
        public void run(){
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(mrun);
    }

}
