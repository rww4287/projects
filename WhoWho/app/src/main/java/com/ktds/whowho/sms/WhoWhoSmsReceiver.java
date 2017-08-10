package com.ktds.whowho.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.ktds.whowho.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class WhoWhoSmsReceiver extends BroadcastReceiver {

    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        if ( intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED") ){

            Bundle bundle = intent.getExtras();
            Object[] messages = (Object[]) bundle.get("pdus"); // sms객체를 가져온다.
            SmsMessage smsMessage[] = new SmsMessage[messages.length];

            String phoneNumber = "";
            String message = "";
            String name = "";

            for( int i = 0; i< smsMessage.length; i++) {
                // 문자를 가지고온다.
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);

                phoneNumber = smsMessage[i].getOriginatingAddress();
                message = smsMessage[i].getMessageBody().toString();

                name = smsMessage[i].getDisplayOriginatingAddress();
                getName(phoneNumber,message);
            }
        }

    }

    private String getName(final String phoneNumber, final String message ) {

        class GetSpamDataTask extends AsyncTask<String, Void, String> {

            private String baseURL = "http://kr.tellows.asia/num/";
            private String brandName;
            @Override
            protected String doInBackground(String... params) {

                this.brandName = params[0]; // 전달받은 상호명을 brandName에 넣어라.
                String result = "";

                try {
                    Document document = Jsoup.connect(baseURL + phoneNumber).get();
                    Elements span = document.select("#details > div.box3 > div > p > span:nth-child(2)");

                    String spamData = "+" + span.text();
                    Log.d("HTML",spamData);

                    result = spamData; // 스팸 여부

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                // TODO Activity 띄우기.
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("brandName", brandName);
                intent.putExtra("phoneNumber", phoneNumber);
                intent.putExtra("spamInfo", s);
                intent.putExtra("message", message);

                context.startActivity(intent);
            }
        }


        class GetNameTask extends AsyncTask<String, Void, String> {
            private String baseURL = "https://number.whoscall.com/ko-KR/kr/";

            @Override
            protected String doInBackground(String... params) {

                String result = "";
                try {
                    Document document = Jsoup.connect(baseURL + params[0]).get();
                    Elements anchor = document.select("div.site-container > div.site-main > div > div > div.ndp-container__right > div.number-info > h1");

                    String name =  anchor.text();

                    result = name; // 상호명
                    Log.d("HTML",name);
                    return result;

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return result; // 전화번호와 상호명을 동시에 넘긴다.
            }

            @Override
            protected void onPostExecute(String s) {
                GetSpamDataTask task = new GetSpamDataTask();
                task.execute(s);
            }
        }


        GetNameTask task = new GetNameTask();
        task.execute(phoneNumber);
        return "";

    }

}
