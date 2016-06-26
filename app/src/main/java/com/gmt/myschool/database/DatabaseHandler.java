package com.gmt.myschool.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;

/**
 * Created by user on 6/6/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "media";
    private static final String TABLE_IMAGES = "images";
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE = "image";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_IMAGES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_IMAGE + " BLOB"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES);

        // Create tables again
        onCreate(db);
    }

    public long insertImage(byte[] img) {
        SQLiteDatabase db = this.getWritableDatabase();
        byte yes[] = img;
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_IMAGE, yes);
        return db.insert(TABLE_IMAGES, null, initialValues);

    }

    public Bitmap readImage(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_IMAGES + " WHERE " + KEY_ID + "=?", new String[]{id});
        if (cursor.moveToFirst()) {
            byte[] bytes = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE));
            ByteArrayInputStream imageStream = new ByteArrayInputStream(bytes);
            return BitmapFactory.decodeStream(imageStream);
        }
        return null;
    }
}