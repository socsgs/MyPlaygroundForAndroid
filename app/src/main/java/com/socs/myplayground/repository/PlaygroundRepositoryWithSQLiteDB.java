package com.socs.myplayground.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.socs.myplayground.model.User;
import com.socs.myplayground.repository.db.PlaygroundContract;
import com.socs.myplayground.repository.db.PlaygroundDatabaseHelper;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by GeethaS on 7/17/2014.
 */
public class PlaygroundRepositoryWithSQLiteDB implements IPlaygroundRepository {
    private final static String TAG = "PlaygroundRepositoryWithSQLiteDB";

    PlaygroundDatabaseHelper dbHelper;
    public PlaygroundRepositoryWithSQLiteDB(Context context) {
        dbHelper = new PlaygroundDatabaseHelper(context);
        for(User user: getAllUserFromJsonString()) {
            insertData(user);
        }
    }

    @Override
    public User getUser(long id) {
        return null;
    }

    @Override
    public User getUser(String emailAddress) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                PlaygroundContract.User._ID,
                PlaygroundContract.User.COLUMN_NAME_USER_ID,
                PlaygroundContract.User.COLUMN_NAME_USER_NAME,
                PlaygroundContract.User.COLUMN_NAME_USER_EMAIL_ADDRESS,
                PlaygroundContract.User.COLUMN_NAME_USER_PASSWORD
        };

        String sortOrder = PlaygroundContract.User.COLUMN_NAME_USER_EMAIL_ADDRESS + " ASC";

        User user = null;
        if (db != null) {
            Cursor c = db.query(
                    PlaygroundContract.User.TABLE_NAME,
                    projection,
                    null,
                    null,
                    null,
                    null,
                    sortOrder
            );

            c.moveToFirst();
            int columnIndexUserId = c.getColumnIndex(PlaygroundContract.User.COLUMN_NAME_USER_ID);
            int columnIndexUserName = c.getColumnIndex(PlaygroundContract.User.COLUMN_NAME_USER_NAME);
            int columnIndexUserEmailAddress = c.getColumnIndex(PlaygroundContract.User.COLUMN_NAME_USER_EMAIL_ADDRESS);
            int columnIndexUserPassword = c.getColumnIndex(PlaygroundContract.User.COLUMN_NAME_USER_PASSWORD);
            while(c.moveToFirst()) {
                if(c.getString(columnIndexUserEmailAddress).equals(emailAddress)) {
                    user = new User();
                    user.setId(c.getInt(columnIndexUserId));
                    user.setName(c.getString(columnIndexUserName));
                    user.setEmailAddress(c.getString(columnIndexUserEmailAddress));
                    user.setPassword(c.getString(columnIndexUserPassword));
                    break;
                }
            }

            c.close();
            db.close();
        }

        return user;
    }

    @Override
    public void createUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    private void insertData(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
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

