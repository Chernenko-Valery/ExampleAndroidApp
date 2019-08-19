package com.example.intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TEXT_TAG = "text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle extra = getIntent().getExtras();
        if(extra!=null) {
            TextView textView = findViewById(R.id.receiveText);
            textView.setText(extra.getString(MainActivity.SEND_TEXT_TAG));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        TextView textView = findViewById(R.id.receiveText);
        outState.putString(TEXT_TAG, textView.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView textView = findViewById(R.id.receiveText);
        textView.setText(savedInstanceState.getString(TEXT_TAG));
    }
}
