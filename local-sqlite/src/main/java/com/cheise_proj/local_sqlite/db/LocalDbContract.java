package com.cheise_proj.local_sqlite.db;

import android.provider.BaseColumns;

/**
 * LocalDbContract class, provide table schema for db
 * class implements BaseColumns from provider
 */
class LocalDbContract {
    static final class UserTable implements BaseColumns {
        static final String TABLE_NAME = "users";
        static final String COLUMN_USERNAME = "username";
        static final String COLUMN_EMAIL = "email";
        static final String COLUMN_PASSWORD = "password";
        static final String COLUMN_IMAGE = "image";

        static final String SQL_CREATE_TABLE = "" +
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + COLUMN_USERNAME + " TEXT NOT NULL UNIQUE, " +
                "" + COLUMN_EMAIL + " TEXT NOT NULL, " +
                "" + COLUMN_PASSWORD + " TEXT NOT NULL, " +
                "" + COLUMN_IMAGE + " TEXT NULL )";
    }

    static final class ContactTable implements BaseColumns{
        static final String TABLE_NAME = "contact";
        static final String COLUMN_NAME = "contact_name";
        static final String COLUMN_TEL = "contact_tel";
        static final String COLUMN_EMAIL = "contact_email";
        static final String COLUMN_USER_ID = "userId";

        static final String SQL_CREATE_TABLE = "" +
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "" + COLUMN_NAME + " TEXT NOT NULL, " +
                "" + COLUMN_TEL + " TEXT NOT NULL, " +
                "" + COLUMN_EMAIL + " TEXT NULL, " +
                "" + COLUMN_USER_ID + " INTEGER NOT NULL )";
    }
}
