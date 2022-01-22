package com.example.test.books;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BookDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DB_NAME";
    private static final int VERSION = 1;

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + BookContract.TABLE_NAME + " ("
            + BookContract.ID + " INTEGER PRIMARY KEY, "
            + BookContract.BOOK_NAME + " TEXT, "
            + BookContract.YEAR_OF_RELEASE + " INTEGER NOT NULL)";

    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + BookContract.TABLE_NAME;

    public BookDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }

    public void insert(String name, int year) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(BookContract.BOOK_NAME, name);
        contentValues.put(BookContract.YEAR_OF_RELEASE, year);

        getWritableDatabase().insert(BookContract.TABLE_NAME, null, contentValues);

    }

    public int update(long id, String newGunName) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(BookContract.BOOK_NAME, newGunName);

        String where = BookContract.ID + " = ?";
        String[] args = new String[]{
                String.valueOf(id)
        };

        return getWritableDatabase().update(
                BookContract.TABLE_NAME,
                contentValues,
                where,
                args
        );

    }

    public int delete(long gunId) {

        String where = BookContract.ID + " = ?";
        String[] args = new String[]{
                String.valueOf(gunId)
        };

        return getWritableDatabase().delete(BookContract.TABLE_NAME, where, args);

    }

    public int deleteAll() {
        return getWritableDatabase().delete(BookContract.TABLE_NAME, null, null);
    }

    @SuppressLint("Range")
    public List<BookModel> select(String name) {

        String[] projection = new String[]{
                BookContract.BOOK_NAME, BookContract.YEAR_OF_RELEASE
        };

        String where = BookContract.BOOK_NAME + " LIKE ?";
        String[] args = new String[]{
                "%" + name + "%"
        };

        String ordering = BookContract.YEAR_OF_RELEASE + " DESC";

        @SuppressLint("Recycle") Cursor cursor = getReadableDatabase().query(
                BookContract.TABLE_NAME,
                projection,
                where,
                args,
                null,
                null,
                ordering
        );

        List<BookModel> guns = new ArrayList<>();

        while (cursor.moveToNext()) {
            guns.add(new BookModel(
                    cursor.getString(cursor.getColumnIndex(BookContract.BOOK_NAME)),
                    cursor.getInt(cursor.getColumnIndex(BookContract.YEAR_OF_RELEASE))
            ));
        }

        return guns;

    }
}