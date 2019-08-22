package com.example.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.CancellationSignal;

public class PeopleProvider extends ContentProvider {

    private static final String PEOPLE_PROVIDER_LOG = "peopleProvider";

    private static final int PEOPLE = 100;
    private static final int PEOPLE_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(PeopleContract.CONTENT_AUTHORITY, PeopleContract.PATH_PEOPLE, PEOPLE);
        sUriMatcher.addURI(PeopleContract.CONTENT_AUTHORITY, PeopleContract.PATH_PEOPLE + "/#", PEOPLE_ID);
    }

    private PeopleDBHelper mDBHelper;

    @Override
    public boolean onCreate() {
        mDBHelper = new PeopleDBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDBHelper.getReadableDatabase();
        Cursor cursor;
        int match = sUriMatcher.match(uri);
        switch (match) {
            case PEOPLE:
                cursor = database.query(PeopleContract.PeopleEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
            case PEOPLE_ID:
                selection = PeopleContract.PeopleEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(PeopleContract.PeopleEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
        }
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case PEOPLE:
                return insertPeople(uri, contentValues);
            default:
                return null;
        }
    }

    private Uri insertPeople(Uri uri, ContentValues contentValues) {
        SQLiteDatabase database = mDBHelper.getWritableDatabase();
        long id = database.insert(PeopleContract.PeopleEntry.TABLE_NAME, null, contentValues);
        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
