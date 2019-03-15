package com.andres.appmoviles.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "AppMoviles";
    public static final int DB_VERSION = 1;

    public static final String FRIENDS_TABLE = "friends";
    public static final String FRIENDS_ID = "id";
    public static final String FRIENDS_NAME = "name";
    public static final String FRIENDS_AGE = "age";
    public static final String FRIENDS_PHONE = "phone";
    public static final String FRIENDS_EMAIL = "email";

    public static final String CREATE_FRIENDS_TABLE = "CREATE TABLE A(ID TEXT PRIMARY KEY, NAME TEXT, AGE TEXT, PHONE TEXT, EMAIL TEXT )";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
