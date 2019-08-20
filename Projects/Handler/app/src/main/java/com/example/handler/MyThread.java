package com.example.handler;


import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import java.util.Random;

class MyThread extends Thread {
    private static final int MAX_LENGTH = 15;
    private static final String ALL_SYMBOLS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    Random random = new Random();
    Handler handler;

    public MyThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        while(true) {
            SystemClock.sleep(1000);
            Message msg = new Message();
            msg.what = MainActivity.MSG_CODE;
            msg.obj = getRandString();
            handler.sendMessage(msg);
        }
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
