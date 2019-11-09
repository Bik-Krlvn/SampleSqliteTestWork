package com.cheise_proj.local_sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.cheise_proj.local_sqlite.model.ContactLocal;
import com.cheise_proj.local_sqlite.model.UserLocal;

import java.util.ArrayList;
import java.util.List;
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
     *
     * @param context require context
     * @param dbName  require database name
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
        db.execSQL(LocalDbContract.ContactTable.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + LocalDbContract.UserTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LocalDbContract.ContactTable.TABLE_NAME);
        onCreate(db);
    }

    /**
     * Insert new user to table users
     *
     * @param username  input username
     * @param email     input email address
     * @param password  input password
     * @param imagePath input image path
     * @return inserted row id
     */
    public int insertUser(String username, String email, String password, String imagePath) {
        ContentValues values = new ContentValues();
        values.put(LocalDbContract.UserTable.COLUMN_USERNAME, username);
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

    public UserLocal getUserById(int userId) {
        String[] columns = {
                LocalDbContract.UserTable._ID,
                LocalDbContract.UserTable.COLUMN_USERNAME,
                LocalDbContract.UserTable.COLUMN_EMAIL,
                LocalDbContract.UserTable.COLUMN_PASSWORD,
                LocalDbContract.UserTable.COLUMN_IMAGE,
        };
        String selection =
                LocalDbContract.UserTable._ID + " = ?";
        String[] selectionArgs = {String.valueOf(userId)};
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

    public int insertContact(int userId, String name, String contact, String email) {
        ContentValues values = new ContentValues();
        values.put(LocalDbContract.ContactTable.COLUMN_USER_ID, userId);
        values.put(LocalDbContract.ContactTable.COLUMN_NAME, name);
        values.put(LocalDbContract.ContactTable.COLUMN_TEL, contact);
        values.put(LocalDbContract.ContactTable.COLUMN_EMAIL, email);
        SQLiteDatabase db = this.getWritableDatabase();
        return (int) db.insert(LocalDbContract.ContactTable.TABLE_NAME, null, values);
    }

    public ContactLocal getContactById(int userId, int contactId) {
        ContactLocal contactLocal = new ContactLocal();
        String[] columns = {
                LocalDbContract.ContactTable._ID,
                LocalDbContract.ContactTable.COLUMN_USER_ID,
                LocalDbContract.ContactTable.COLUMN_NAME,
                LocalDbContract.ContactTable.COLUMN_TEL,
                LocalDbContract.ContactTable.COLUMN_EMAIL,
        };
        String selection = LocalDbContract.ContactTable.COLUMN_USER_ID + " = ? AND "
                + LocalDbContract.ContactTable._ID;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(userId), String.valueOf(contactId)};
        Cursor cursor = db.query(
                LocalDbContract.ContactTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if (cursor.getCount() == 0) return contactLocal;
        int idPos = cursor.getColumnIndex(LocalDbContract.ContactTable._ID);
        int userPos = cursor.getColumnIndex(LocalDbContract.ContactTable.COLUMN_USER_ID);
        int namePos = cursor.getColumnIndex(LocalDbContract.ContactTable.COLUMN_NAME);
        int telPos = cursor.getColumnIndex(LocalDbContract.ContactTable.COLUMN_TEL);
        int emailPos = cursor.getColumnIndex(LocalDbContract.ContactTable.COLUMN_EMAIL);

        if (cursor.moveToFirst()) {
            do {
                contactLocal.setId(cursor.getInt(idPos));
                contactLocal.setUserId(cursor.getInt(userPos));
                contactLocal.setName(cursor.getString(namePos));
                contactLocal.setContact(cursor.getString(telPos));
                contactLocal.setEmail(cursor.getString(emailPos));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactLocal;
    }

    public List<ContactLocal> getAllContacts(int userId) {
        List<ContactLocal> contactLocalList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] selectionArgs = {String.valueOf(userId)};
        String[] columns = {
                LocalDbContract.ContactTable._ID,
                LocalDbContract.ContactTable.COLUMN_USER_ID,
                LocalDbContract.ContactTable.COLUMN_NAME,
                LocalDbContract.ContactTable.COLUMN_TEL,
                LocalDbContract.ContactTable.COLUMN_EMAIL,
        };
        String selection = LocalDbContract.ContactTable.COLUMN_USER_ID + " = ?";
        Cursor cursor = db.query(
                LocalDbContract.ContactTable.TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        if (cursor.getCount() == 0) return contactLocalList;
        int idPos = cursor.getColumnIndex(LocalDbContract.ContactTable._ID);
        int userPos = cursor.getColumnIndex(LocalDbContract.ContactTable.COLUMN_USER_ID);
        int namePos = cursor.getColumnIndex(LocalDbContract.ContactTable.COLUMN_NAME);
        int telPos = cursor.getColumnIndex(LocalDbContract.ContactTable.COLUMN_TEL);
        int emailPos = cursor.getColumnIndex(LocalDbContract.ContactTable.COLUMN_EMAIL);

        if (cursor.moveToFirst()) {
            do {
                contactLocalList.add(new ContactLocal(
                        cursor.getInt(idPos),
                        cursor.getInt(userPos),
                        cursor.getString(namePos),
                        cursor.getString(telPos),
                        cursor.getString(emailPos)
                ));

            } while (cursor.moveToNext());
        }
        cursor.close();
        Logger.getLogger("get contacts").log(Level.INFO, "list size: " + contactLocalList.size());
        return contactLocalList;
    }

}
