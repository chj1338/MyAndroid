package com.example.p048293.homework5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class SMSReceiver extends BroadcastReceiver {
    private static final String TAG = "SMSReciever";


    public SMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive 호출됨!!!");

        Bundle bundle = intent.getExtras();
        Object[] objs = (Object[])bundle.get("pdus");

        SmsMessage[] messages = new SmsMessage[objs.length];

        for(int i=0; i<objs.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[])objs[i]);
        }

        String mobile = messages[0].getOriginatingAddress();
        String contents = messages[0].getMessageBody().toString();
        Date recievedDate = new Date(messages[0].getTimestampMillis());

        Log.d(TAG, "발신번호 : " + mobile + ", 내용 : " + contents + ", 수신일시 : " + recievedDate);

        Intent showIntent = new Intent(context, MainActivity.class);
        showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        showIntent.putExtra("index", 0);
        showIntent.putExtra("mobile", mobile);
        showIntent.putExtra("contents", contents);
        showIntent.putExtra("recievedDate", recievedDate.toString());

        context.startActivity(showIntent);
    }
}
