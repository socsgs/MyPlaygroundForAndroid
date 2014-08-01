package com.socs.myplayground.repository.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socs.myplayground.model.User;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by GeethaS on 7/17/2014.
 */
public class PlaygroundDatabaseHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "Playground.db";

    public PlaygroundDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PlaygroundContract.SQL_CREATE_ENTRIES);
        init();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PlaygroundContract.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void init() {
        for(User user: getAllUserFromJsonString()) {
            insertData(user);
        }
    }

    private void insertData(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PlaygroundContract.User.COLUMN_NAME_USER_ID, user.getId());
        values.put(PlaygroundContract.User.COLUMN_NAME_USER_NAME, user.getName());
        values.put(PlaygroundContract.User.COLUMN_NAME_USER_EMAIL_ADDRESS, user.getEmailAddress());
        values.put(PlaygroundContract.User.COLUMN_NAME_USER_PASSWORD, user.getPassword());

        long rowId = -1;
        if (db != null) {
            rowId = db.insert(PlaygroundContract.User.TABLE_NAME, null, values);
        }
    }

    private List<User> getAllUserFromJsonString() {
        String allUsersJsonString  = "[{\"id\":1,\"name\":\"Test, User 1\",\"emailAddress\":\"test1@test.com\",\"password\":\"test123\"},{\"id\":2,\"name\":\"Test, User 2\",\"emailAddress\":\"test2@test.com\",\"password\":\"test123\"},{\"id\":3,\"name\":\"Test, User 3\",\"emailAddress\":\"test3@test.com\",\"password\":\"test123\"},{\"id\":4,\"name\":\"Test, User 4\",\"emailAddress\":\"test4@test.com\",\"password\":\"test123\"},{\"id\":5,\"name\":\"Test, User 5\",\"emailAddress\":\"test5@test.com\",\"password\":\"test123\"}]";
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>(){}.getType();
        List<User> allUsers = gson.fromJson(allUsersJsonString, userListType);

        return allUsers;
    }

}
