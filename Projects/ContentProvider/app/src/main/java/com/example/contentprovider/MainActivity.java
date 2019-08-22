package com.example.contentprovider;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    public static final String FIRST_NAME_TAG = "firstName";
    public static final String LAST_NAME_TAG = "lastName";

    private PeopleDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        helper = new PeopleDBHelper(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data==null) return;
        String firstName = data.getStringExtra(FIRST_NAME_TAG);
        String lastName = data.getStringExtra(LAST_NAME_TAG);
        insertPeople(firstName, lastName);
        displayPeopleInfo();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayPeopleInfo();
    }

    private void displayPeopleInfo() {
        SQLiteDatabase db = helper.getReadableDatabase();

        String[] protection = {
                PeopleContract.PeopleEntry._ID,
                PeopleContract.PeopleEntry.FIRST_NAME,
                PeopleContract.PeopleEntry.LAST_NAME };

        Cursor cursor = db.query(PeopleContract.PeopleEntry.TABLE_NAME, protection, null, null, null, null, null);

        TextView textInfo = findViewById(R.id.textView);

        textInfo.setText("Таблица содержит " + cursor.getCount() + " людей.\n");
        textInfo.append(PeopleContract.PeopleEntry._ID + " - " + PeopleContract.PeopleEntry.LAST_NAME + " - " + PeopleContract.PeopleEntry.LAST_NAME + "\n");

        int _IDIndex = cursor.getColumnIndex(PeopleContract.PeopleEntry._ID);
        int FirstNameIndex = cursor.getColumnIndex(PeopleContract.PeopleEntry.FIRST_NAME);
        int LastNameIndex = cursor.getColumnIndex(PeopleContract.PeopleEntry.LAST_NAME);

        while (cursor.moveToNext()) {
            int _ID = cursor.getInt(_IDIndex);
            String firstName = cursor.getString(FirstNameIndex);
            String lastName = cursor.getString(LastNameIndex);

            textInfo.append(_ID + " - " + firstName + " - " + lastName + "\n");
        }

        cursor.close();
    }

    private void insertPeople(String firstName, String lastName) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(PeopleContract.PeopleEntry.FIRST_NAME, firstName);
        contentValues.put(PeopleContract.PeopleEntry.LAST_NAME, lastName);
        db.insert(PeopleContract.PeopleEntry.TABLE_NAME, null, contentValues);
    }
}
