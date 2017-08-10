package com.ktds.smallsumsettlement;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if(getIntent().getStringExtra("pay") != null ) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_main);

            Intent intent = getIntent();

            String pay = intent.getStringExtra("pay");
            TextView tv_pay = (TextView) findViewById(R.id.tv_pay);

            tv_pay.setText(pay);

        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int result = checkCallingPermission(Manifest.permission.RECEIVE_SMS);

                if (result == PackageManager.PERMISSION_DENIED) {
                    requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 1000);
                }
            } else {
                finish();
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if ( requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_DENIED ) {
            finish();
        }
        else {
            Toast.makeText(this, "SMS를 읽습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        String pay = intent.getStringExtra("pay");
        //Log.d("MAIN",pay+"+");

        TextView tv_pay = (TextView) findViewById(R.id.tv_pay);

        tv_pay.setText(pay);
    }
}
