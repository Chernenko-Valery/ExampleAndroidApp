package com.example.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PeopleDBHelper extends SQLiteOpenHelper {

    private static final String DBHELPER_LOG = "DBHELPER";

    private static final String DATABASE_NAME = "people.db";
    private static final int DATABASE_VERSION = 1;

    public PeopleDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_CREATE_PEOPLE_TABLE = "CREATE TABLE " + PeopleContract.PeopleEntry.TABLE_NAME + " ("
                + PeopleContract.PeopleEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PeopleContract.PeopleEntry.FIRST_NAME + " TEXT NOT NULL, "
                + PeopleContract.PeopleEntry.LAST_NAME + " TEXT NOT NULL );";
        sqLiteDatabase.execSQL(SQL_CREATE_PEOPLE_TABLE);
        Log.d(DBHELPER_LOG, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(DBHELPER_LOG, "onUpgrade");
    }
}
