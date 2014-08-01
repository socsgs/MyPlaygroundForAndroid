package com.socs.myplayground.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.socs.myplayground.model.User;
import com.socs.myplayground.repository.db.PlaygroundContract;
import com.socs.myplayground.repository.db.PlaygroundDatabaseHelper;

/**
 * Created by GeethaS on 7/17/2014.
 */
public class PlaygroundRepositoryWithSQLiteDB implements IPlaygroundRepository {
    private final static String TAG = "PlaygroundRepositoryWithSQLiteDB";

    PlaygroundDatabaseHelper dbHelper;
    public PlaygroundRepositoryWithSQLiteDB(Context context) {
        dbHelper = new PlaygroundDatabaseHelper(context);
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

            boolean recordExists = c.moveToFirst();
            int columnIndexUserId = c.getColumnIndex(PlaygroundContract.User.COLUMN_NAME_USER_ID);
            int columnIndexUserName = c.getColumnIndex(PlaygroundContract.User.COLUMN_NAME_USER_NAME);
            int columnIndexUserEmailAddress = c.getColumnIndex(PlaygroundContract.User.COLUMN_NAME_USER_EMAIL_ADDRESS);
            int columnIndexUserPassword = c.getColumnIndex(PlaygroundContract.User.COLUMN_NAME_USER_PASSWORD);
            while(recordExists) {
                if(c.getString(columnIndexUserEmailAddress).equals(emailAddress)) {
                    user = new User();
                    user.setId(c.getInt(columnIndexUserId));
                    user.setName(c.getString(columnIndexUserName));
                    user.setEmailAddress(c.getString(columnIndexUserEmailAddress));
                    user.setPassword(c.getString(columnIndexUserPassword));
                    break;
                }
                recordExists = c.moveToNext();
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
}

