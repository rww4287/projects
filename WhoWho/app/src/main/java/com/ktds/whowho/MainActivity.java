package com.ktds.whowho;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.style.BulletSpan;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if( getIntent().getStringExtra("message") != null ) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
            //setTheme(R.style.Theme_AppCompat_Dialog);
            setContentView(R.layout.activity_main);

            Intent intent = getIntent();
            String brandName = intent.getStringExtra("brandName");
            String phoneNumber = intent.getStringExtra("phoneNumber");
            String spamInfo = intent.getStringExtra("spamInfo");
            String message = intent.getStringExtra("message");

            // 메모리 때문에 안쪽에 선언.
            TextView tv_brand_name = (TextView) findViewById(R.id.tv_brand_name);
            TextView tv_phone_number = (TextView) findViewById(R.id.tv_phone_number);
            TextView tv_spam_data = (TextView) findViewById(R.id.tv_spam_data);
            TextView tv_message = (TextView) findViewById(R.id.tv_message);

            tv_brand_name.setText(brandName);
            tv_phone_number.setText(phoneNumber);
            tv_spam_data.setText(spamInfo);
            tv_message.setText(message);

        }
        else {
            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
                int result = checkSelfPermission(Manifest.permission.RECEIVE_SMS);

                if ( result == PackageManager.PERMISSION_DENIED ) {
                    requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},1000);
                }
                else {
                    finish();
                }
            }

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if ( requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_DENIED ) {
            finish();
        }
        else {
            Toast.makeText(this, "WhoWho가 SMS를 읽습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    // 메세지를 보는와중에 또 메세지가 왔을때.
    @Override
    protected void onNewIntent(Intent intent) {

        String brandName = intent.getStringExtra("brandName");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String spamInfo = intent.getStringExtra("spamInfo");
        String message = intent.getStringExtra("message");

        // 메모리 때문에 안쪽에 선언.
        TextView tv_brand_name = (TextView) findViewById(R.id.tv_brand_name);
        TextView tv_phone_number = (TextView) findViewById(R.id.tv_phone_number);
        TextView tv_spam_data = (TextView) findViewById(R.id.tv_spam_data);
        TextView tv_message = (TextView) findViewById(R.id.tv_message);

        tv_brand_name.setText(brandName);
        tv_phone_number.setText(phoneNumber);
        tv_spam_data.setText(spamInfo);
        tv_message.setText(message);
    }
}
