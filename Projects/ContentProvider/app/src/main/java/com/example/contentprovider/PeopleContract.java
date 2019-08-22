package com.example.contentprovider;

import android.net.Uri;
import android.provider.BaseColumns;

public final class PeopleContract {

    public static final String CONTENT_AUTHORITY = "com.example.contentprovider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_PEOPLE = "people";

    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PEOPLE);

    public static final class PeopleEntry implements BaseColumns {
        public final static String TABLE_NAME = "people";

        public final static String _ID = BaseColumns._ID;
        public final static String FIRST_NAME = "firstName";
        public final static String LAST_NAME = "lastName";
    }

}
