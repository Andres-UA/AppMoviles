package com.andres.appmoviles.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.andres.appmoviles.model.Friend;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static DBHandler instance = null;

    public static final String DB_NAME = "AppMoviles";
    public static final int DB_VERSION = 2;

    // Tabla amigos
    public static final String FRIENDS_TABLE = "friends";
    public static final String FRIENDS_ID = "id";
    public static final String FRIENDS_NAME = "name";
    public static final String FRIENDS_AGE = "age";
    public static final String FRIENDS_PHONE = "phone";
    public static final String FRIENDS_EMAIL = "email";
    public static final String FRIENDS_USER_ID = "userid";
    public static final String CREATE_FRIENDS_TABLE = "CREATE TABLE " + FRIENDS_TABLE + " (" + FRIENDS_ID + " TEXT PRIMARY KEY, " + FRIENDS_NAME + " TEXT, " + FRIENDS_AGE + " TEXT, " + FRIENDS_PHONE + " TEXT, " + FRIENDS_EMAIL + " TEXT, " + FRIENDS_USER_ID + " TEXT )";

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
        db.execSQL("INSERT INTO " + FRIENDS_TABLE + " (" + FRIENDS_ID + ", " + FRIENDS_NAME + ", " + FRIENDS_AGE + ", " + FRIENDS_PHONE + ", " + FRIENDS_EMAIL + ", " + FRIENDS_USER_ID + " ) VALUES ('" + friend.getId() + "', '" + friend.getName() + "', '" + friend.getAge() + "', '" + friend.getPhone() + "', '" + friend.getEmail() + "', '" + friend.getUserId() + "')");
        db.close();
    }

    // Leer
    public ArrayList<Friend> getAllFriendByUser(String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Friend> friends = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + FRIENDS_TABLE + " WHERE " + FRIENDS_USER_ID + "='" + uid + "'", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Friend friend = new Friend();
                friend.setId(cursor.getString(cursor.getColumnIndex(FRIENDS_ID)));
                friend.setName(cursor.getString(cursor.getColumnIndex(FRIENDS_NAME)));
                friend.setAge(cursor.getString(cursor.getColumnIndex(FRIENDS_AGE)));
                friend.setPhone(cursor.getString(cursor.getColumnIndex(FRIENDS_PHONE)));
                friend.setEmail(cursor.getString(cursor.getColumnIndex(FRIENDS_EMAIL)));
                friend.setUserId(cursor.getString(cursor.getColumnIndex(FRIENDS_USER_ID)));
                friends.add(friend);
            } while (cursor.moveToNext());
        }
        db.close();
        return friends;
    }

    public void deleteFriendsByUser(String uid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + FRIENDS_TABLE + " WHERE " + FRIENDS_USER_ID + "='" + uid + "'");
        db.close();
    }
}
