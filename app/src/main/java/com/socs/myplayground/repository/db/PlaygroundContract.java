package com.socs.myplayground.repository.db;

import android.provider.BaseColumns;

/**
 * Created by GeethaS on 7/17/2014.
 */
public final class PlaygroundContract {
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private PlaygroundContract() {}

    public static abstract class User implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_NAME_USER_ID = "user_id";
        public static final String COLUMN_NAME_USER_EMAIL_ADDRESS = "user_email_address";
        public static final String COLUMN_NAME_USER_PASSWORD = "user_password";
        public static final String COLUMN_NAME_USER_NAME = "user_name";

        public static final int COLUMN_POSITION_USER_ID = 1;
        public static final int COLUMN_POSITION_USER_EMAIL_ADDRESS = 2;
        public static final int COLUMN_POSITION_USER_PASSWORD = 3;
        public static final int COLUMN_POSITION_USER_NAME = 4;

    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + User.TABLE_NAME + " (" +
            User._ID + " INTEGER PRIMARY KEY," +
            User.COLUMN_NAME_USER_ID + TEXT_TYPE + COMMA_SEP +
            User.COLUMN_NAME_USER_EMAIL_ADDRESS + TEXT_TYPE + COMMA_SEP +
            User.COLUMN_NAME_USER_PASSWORD + TEXT_TYPE + COMMA_SEP +
            User.COLUMN_NAME_USER_NAME + TEXT_TYPE +
            " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + User.TABLE_NAME;
}
