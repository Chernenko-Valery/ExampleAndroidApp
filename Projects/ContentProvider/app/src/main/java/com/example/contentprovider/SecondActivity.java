package com.example.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText firstNameText = findViewById(R.id.firstName);
                EditText lastNameText = findViewById(R.id.lastName);
                String firstName = firstNameText.getText().toString();
                String lastName = lastNameText.getText().toString();
                if(firstName == null || lastName == null || firstName.equals("") || lastName.equals("")) return;
                Intent intent = new Intent();
                intent.putExtra(MainActivity.FIRST_NAME_TAG, firstName);
                intent.putExtra(MainActivity.LAST_NAME_TAG, lastName);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


}
