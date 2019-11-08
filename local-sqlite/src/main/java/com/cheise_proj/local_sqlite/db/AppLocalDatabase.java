package com.cheise_proj.local_sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.cheise_proj.local_sqlite.model.UserLocal;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;

/**
 * AppLocalDatabase, creates sqLite database
 * class extends SQLiteOpenHelper {@Link https://developers.android.com}
 */
public class AppLocalDatabase extends SQLiteOpenHelper {
    private static AppLocalDatabase INSTANCE = null;

    @Inject
    public AppLocalDatabase(@Nullable Context context, String dbName) {
        super(context, dbName, null, 1);
    }

    /**
     * Get instance of local db
     * @param context require context
     * @param dbName require database name
     * @return instance of local db
     */
    public static AppLocalDatabase getInstance(Context context, String dbName) {
        if (INSTANCE == null) {
            synchronized (AppLocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppLocalDatabase(context, dbName);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LocalDbContract.UserTable.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LocalDbContract.UserTable.TABLE_NAME);
        onCreate(db);
    }

    /**
     * Insert new user to table users
     * @param username input username
     * @param email input email address
     * @param password input password
     * @param imagePath input image path
     * @return inserted row id
     */
    public int insertUser(String username, String email, String password,String imagePath) {
        ContentValues values = new ContentValues();
        values.put(LocalDbContract.UserTable.COLUMN_USERNAME,username);
        values.put(LocalDbContract.UserTable.COLUMN_EMAIL, email);
        values.put(LocalDbContract.UserTable.COLUMN_PASSWORD, password);
        values.put(LocalDbContract.UserTable.COLUMN_IMAGE, imagePath);

        SQLiteDatabase db = this.getWritableDatabase();
        long insert = db.insert(LocalDbContract.UserTable.TABLE_NAME, null, values);
        return (int) insert;
    }

    public UserLocal getUser(String username, String password) {
        String[] columns = {
                LocalDbContract.UserTable._ID,
                LocalDbContract.UserTable.COLUMN_USERNAME,
                LocalDbContract.UserTable.COLUMN_EMAIL,
                LocalDbContract.UserTable.COLUMN_PASSWORD,
                LocalDbContract.UserTable.COLUMN_IMAGE,
        };
        String selection =
                LocalDbContract.UserTable.COLUMN_USERNAME + " = ? AND " +
                        LocalDbContract.UserTable.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                LocalDbContract.UserTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null,
                null
        );
        int idPos = cursor.getColumnIndex(LocalDbContract.UserTable._ID);
        int userPos = cursor.getColumnIndex(LocalDbContract.UserTable.COLUMN_USERNAME);
        int emailPos = cursor.getColumnIndex(LocalDbContract.UserTable.COLUMN_EMAIL);
        int passPos = cursor.getColumnIndex(LocalDbContract.UserTable.COLUMN_PASSWORD);
        int imagePos = cursor.getColumnIndex(LocalDbContract.UserTable.COLUMN_IMAGE);

        UserLocal userLocal = new UserLocal();

        if (cursor.moveToFirst()) {
            do {
                userLocal.setId(cursor.getInt(idPos));
                userLocal.setUsername(cursor.getString(userPos));
                userLocal.setEmail(cursor.getString(emailPos));
                userLocal.setPassword(cursor.getString(passPos));
                userLocal.setImagePath(cursor.getString(imagePos));

            } while (cursor.moveToNext());
        }
        cursor.close();
        Logger.getLogger("getUser").log(Level.INFO, "userId: " + userLocal.getId());
        return userLocal;
    }


}
