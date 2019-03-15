package com.andres.appmoviles.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andres.appmoviles.model.Friend;

public class DBHandler extends SQLiteOpenHelper {

    private static DBHandler instance = null;

    public static final String DB_NAME = "AppMoviles";
    public static final int DB_VERSION = 1;

    // Tabla amigos
    public static final String FRIENDS_TABLE = "friends";
    public static final String FRIENDS_ID = "id";
    public static final String FRIENDS_NAME = "name";
    public static final String FRIENDS_AGE = "age";
    public static final String FRIENDS_PHONE = "phone";
    public static final String FRIENDS_EMAIL = "email";
    public static final String CREATE_FRIENDS_TABLE = "CREATE TABLE " + FRIENDS_TABLE + " (" + FRIENDS_ID + " TEXT PRIMARY KEY, " + FRIENDS_NAME + " TEXT, " + FRIENDS_AGE + " TEXT, " + FRIENDS_PHONE + " TEXT, " + FRIENDS_EMAIL + " TEXT )";

    public static synchronized DBHandler getInstance(Context context) {
        if (instance == null) {
            instance = new DBHandler(context);
        }
        return instance;
    }

    private DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_FRIENDS_TABLE);
    }

    // Se produce cuando aumentamos la versión de la base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FRIENDS_TABLE);
        onCreate(db);
    }

    // CRUD Amigos

    // Crear
    public void createFriend(Friend friend) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + FRIENDS_TABLE + " (" + FRIENDS_ID + ", " + FRIENDS_NAME + ", " + FRIENDS_AGE + ", " + FRIENDS_PHONE + ", " + FRIENDS_EMAIL + ") VALUES ('" + friend.getId() + "','" + friend.getName() + "','" + friend.getAge() + "','" + friend.getPhone() + "','" + friend.getEmail() + "')");
        db.close();
    }

}
