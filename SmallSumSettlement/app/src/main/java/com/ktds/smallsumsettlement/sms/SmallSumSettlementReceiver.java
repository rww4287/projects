package com.ktds.smallsumsettlement.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.ktds.smallsumsettlement.MainActivity;

public class SmallSumSettlementReceiver extends BroadcastReceiver {

    private Context context;
    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;

        if ( intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED") ) {
            Bundle bundle = intent.getExtras();
            Object[] messages = (Object[]) bundle.get("pdus");
            SmsMessage smsMessage[] = new SmsMessage[messages.length];

            String phoneNumber = "";
            String message = "";

            for ( int i = 0; i< smsMessage.length; i++){
                smsMessage[i] = SmsMessage.createFromPdu((byte[]) messages[i]);

                phoneNumber = smsMessage[i].getOriginatingAddress();
                message = smsMessage[i].getMessageBody().toString();
                Log.d("SB",phoneNumber);

                if ( phoneNumber.equals("15663754") ){
                    String data = new String();
                    getData(message);

                }
                else {
                    // X
                }

            }

        }
    }

    private void getData( final String message ){
        String msg[] = message.split(" ");
        // Log.d("DATA",msg[3]+"++");
        String result = msg[3].replaceAll("[^0-9]","");

        Log.d("DATA",result+"+");

        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("pay",result);

        Log.d("DATA",result+"+");

        context.startActivity(intent);


    }
}
