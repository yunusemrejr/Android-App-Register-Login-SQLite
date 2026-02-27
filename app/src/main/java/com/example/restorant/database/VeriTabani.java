package com.example.restorant.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.restorant.utils.PasswordUtils;

public class VeriTabani extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Giris.db";
    private static final int DATABASE_VERSION = 2;
    
    // Table and column names
    private static final String TABLE_USERS = "kullanicilar";
    private static final String COLUMN_USERNAME = "kad";
    private static final String COLUMN_PASSWORD = "ksifre";
    private static final String COLUMN_SALT = "salt";

    public VeriTabani(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT NOT NULL, " +
                COLUMN_SALT + " TEXT NOT NULL)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Add salt column to existing table
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }

    /**
     * Inserts a new user with hashed password
     */
    public boolean veriyapistir(String username, String password) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            
            // Generate salt and hash password
            String salt = PasswordUtils.generateSalt();
            String hashedPassword = PasswordUtils.hashPassword(password, salt);
            
            ContentValues values = new ContentValues();
            values.put(COLUMN_USERNAME, username.trim());
            values.put(COLUMN_PASSWORD, hashedPassword);
            values.put(COLUMN_SALT, salt);
            
            long result = db.insert(TABLE_USERS, null, values);
            return result != -1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    /**
     * Checks if username already exists
     */
    public boolean kadkontrol(String username) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            cursor = db.query(TABLE_USERS, 
                    new String[]{COLUMN_USERNAME}, 
                    COLUMN_USERNAME + " = ?", 
                    new String[]{username.trim()}, 
                    null, null, null);
            return cursor.getCount() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }

    /**
     * Verifies username and password with secure hashing
     */
    public boolean kadveksifrekontrol(String username, String password) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            cursor = db.query(TABLE_USERS, 
                    new String[]{COLUMN_PASSWORD, COLUMN_SALT}, 
                    COLUMN_USERNAME + " = ?", 
                    new String[]{username.trim()}, 
                    null, null, null);
            
            if (cursor != null && cursor.moveToFirst()) {
                int passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);
                int saltIndex = cursor.getColumnIndex(COLUMN_SALT);
                
                if (passwordIndex != -1 && saltIndex != -1) {
                    String storedHash = cursor.getString(passwordIndex);
                    String salt = cursor.getString(saltIndex);
                    return PasswordUtils.verifyPassword(password, salt, storedHash);
                }
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null && db.isOpen()) {
                db.close();
            }
        }
    }
}
