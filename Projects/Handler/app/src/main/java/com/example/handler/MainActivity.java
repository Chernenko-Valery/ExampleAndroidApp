package com.example.handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int MSG_CODE = 1;

    private static final String TEXT_TAG = "text";

    TextView text;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.randText);
        if(savedInstanceState!= null)
            text.setText(savedInstanceState.getString(TEXT_TAG));

        mHandler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == MSG_CODE)
                    text.setText(msg.obj.toString());
            }
        };

        MyThread myThread = new MyThread(mHandler);
        myThread.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(TEXT_TAG, text.getText().toString());
        super.onSaveInstanceState(outState);
    }
}

