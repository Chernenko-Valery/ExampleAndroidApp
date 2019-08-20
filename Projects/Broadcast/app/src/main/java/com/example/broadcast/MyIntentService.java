package com.example.broadcast;

import android.app.IntentService;
import android.content.Intent;

import java.util.Random;


public class MyIntentService extends IntentService {

    private static final int MAX_LENGTH = 15;
    private static final String ALL_SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    public static final String RAND_STRING_ACTION = "RAND_STRING_ACTION";
    public static final String RAND_STRING_TAG = "randString";

    Random random = new Random();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent i = new Intent(RAND_STRING_ACTION);
        i.putExtra(RAND_STRING_TAG, getRandString());
        sendBroadcast(i);
    }

    private String getRandString() {
        char[] text = new char[MAX_LENGTH];
        for (int i = 0; i < MAX_LENGTH; i++)
        {
            text[i] = ALL_SYMBOLS.charAt(random.nextInt(ALL_SYMBOLS.length()));
        }
        return new String(text);
    }
}
