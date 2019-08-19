package com.example.intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String SEND_TEXT_TAG = "sendText";
    private static final String TEXT_TAG = "text";
    private static final String SEND_INTENT_TAG = "com.example.intent.send";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button startButton = findViewById(R.id.startActivityButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                EditText editText = findViewById(R.id.sendText);
                intent.putExtra(SEND_TEXT_TAG, editText.getText().toString());
                startActivity(intent);
            }
        });

        Button startButton2 = findViewById(R.id.startActivityButton2);
        startButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SEND_INTENT_TAG);
                EditText editText = findViewById(R.id.sendText);
                intent.putExtra(SEND_TEXT_TAG, editText.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        EditText editText = findViewById(R.id.sendText);
        outState.putString(TEXT_TAG, editText.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText editText = findViewById(R.id.sendText);
        editText.setText(savedInstanceState.getString(TEXT_TAG));
    }
}
