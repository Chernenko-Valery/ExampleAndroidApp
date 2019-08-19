package com.example.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String ACTIVITY_LIFE_CYCLE_LOG_TAG = "Activity Life Cycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(ACTIVITY_LIFE_CYCLE_LOG_TAG, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ACTIVITY_LIFE_CYCLE_LOG_TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(ACTIVITY_LIFE_CYCLE_LOG_TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(ACTIVITY_LIFE_CYCLE_LOG_TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ACTIVITY_LIFE_CYCLE_LOG_TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(ACTIVITY_LIFE_CYCLE_LOG_TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(ACTIVITY_LIFE_CYCLE_LOG_TAG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(ACTIVITY_LIFE_CYCLE_LOG_TAG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ACTIVITY_LIFE_CYCLE_LOG_TAG, "onDestroy");
    }
}
