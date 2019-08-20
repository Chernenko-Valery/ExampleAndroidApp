package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service {

    private static final String SERVICE_LOG = "serviceLog";
    private int count;

    @Override
    public void onCreate() {
        super.onCreate();
        count = 0;
        Log.d(SERVICE_LOG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(SERVICE_LOG, "onStartCommand");
        doSomething();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(SERVICE_LOG, "onDestroy");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(SERVICE_LOG, "onBind");
        return null;
    }

    void doSomething() {
        SystemClock.sleep(1000);
        count++;
        Log.d(SERVICE_LOG, "doSomething: " + count);
    }
}
