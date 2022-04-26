package com.example.restorant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class VeriTabani extends SQLiteOpenHelper {

    public static final String GIRIS_DB = "Giris.db";

    public VeriTabani(Context context) {
        super(context, "Giris.db", null, 1);

    }


    @Override


    public void onCreate(SQLiteDatabase veritabanim) {

        veritabanim.execSQL("create Table kullanicilar(kad TEXT primary key, ksifre TEXT)");
    }


    @Override


    public void onUpgrade(SQLiteDatabase veritabanim, int i, int i1) {

        veritabanim.execSQL("drop Table if exists kullanicilar");

    }

    public Boolean veriyapistir(String kad, String ksifre){

        SQLiteDatabase veritabanim = this.getWritableDatabase();

        ContentValues contentValues= new ContentValues();

        contentValues.put("kad", kad);

        contentValues.put("ksifre", ksifre);

        long result = veritabanim.insert("kullanicilar", null, contentValues);

        return result != -1;

    }



    public Boolean kadkontrol(String kad) {

        SQLiteDatabase veritabanim = this.getWritableDatabase();

        Cursor cursor = veritabanim.rawQuery("Select * from kullanicilar where kad = ?", new String[]{kad});

        return cursor.getCount() > 0;
    }



    public Boolean kadveksifrekontrol(String kad, String ksifre){

        SQLiteDatabase veritabanim = this.getWritableDatabase();

        Cursor cursor = veritabanim.rawQuery("Select * from kullanicilar where kad = ? and ksifre = ?", new String[] {kad,ksifre});

        return cursor.getCount() > 0;
    }
}