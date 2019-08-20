package com.example.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

public class MyIntentService extends IntentService {
    private final String INTENT_SERVICE_LOG = "intentServiceLog";

    public static final String TIME_TAG = "time";
    public static final String TASK_TAG = "task";

    public MyIntentService() {
        super("myName");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(INTENT_SERVICE_LOG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int tm = intent.getIntExtra(TIME_TAG, 0);
        String label = intent.getStringExtra(TASK_TAG);
        Log.d(INTENT_SERVICE_LOG, "onHandleIntent start: " + label);
        SystemClock.sleep(tm * 1000);
        Log.d(INTENT_SERVICE_LOG, "onHandleIntent end: " + label);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(INTENT_SERVICE_LOG, "onDestroy");
    }
}
