package com.example.contentprovider;

import android.provider.BaseColumns;

public final class PeopleContract {

    public static final class PeopleEntry implements BaseColumns {
        public final static String TABLE_NAME = "people";

        public final static String _ID = BaseColumns._ID;
        public final static String FIRST_NAME = "firstName";
        public final static String LAST_NAME = "lastName";
    }

}
