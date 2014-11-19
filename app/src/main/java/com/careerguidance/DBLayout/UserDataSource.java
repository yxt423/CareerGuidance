package com.careerguidance.DBLayout;

/**
 * Created by chris on 11/8/14.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.careerguidance.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.COLUMN_USER_ID[0],
            SQLiteHelperClass.COLUMN_USER1[0], SQLiteHelperClass.COLUMN_USER2[0],
            SQLiteHelperClass.COLUMN_USER3[0], SQLiteHelperClass.COLUMN_USER4[0],
            SQLiteHelperClass.COLUMN_USER5[0], SQLiteHelperClass.COLUMN_USER6[0],
            SQLiteHelperClass.COLUMN_USER7[0]};

    public UserDataSource(Context context)
    {
        dbHelper = new SQLiteHelperClass(context);
    }

    public void open() throws SQLException
    {
        database = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    public User createUser(String fName, String lName, String gndr, Date dob, String location, String username, String password)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.COLUMN_USER1[0], fName);
        values.put(SQLiteHelperClass.COLUMN_USER2[0], lName);
        values.put(SQLiteHelperClass.COLUMN_USER3[0], gndr);
        values.put(SQLiteHelperClass.COLUMN_USER4[0], dob.toString());
        values.put(SQLiteHelperClass.COLUMN_USER5[0], location);
        values.put(SQLiteHelperClass.COLUMN_USER6[0], username);
        values.put(SQLiteHelperClass.COLUMN_USER7[0], password);

        long insertId = database.insert(SQLiteHelperClass.TABLE_USER, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TABLE_USER,
                allColumns, SQLiteHelperClass.COLUMN_USER_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        User newUser = cursorToUser(cursor);

        cursor.close();

        return newUser;
    }

    public void delete(User user)
    {
        long id = user.getId();

        database.delete(SQLiteHelperClass.TABLE_USER, SQLiteHelperClass.COLUMN_USER_ID
                + " = " + id, null);
    }

    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(SQLiteHelperClass.TABLE_USER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return users;
    }

    private User cursorToUser(Cursor cursor) {
        User user = new User();

        //user.setId(cursor.getLong(0));

        Date date = new Date();
        user.setFirstName(cursor.getString(1));
        user.setLastName(cursor.getString(2));
        user.setGender(cursor.getString(3));
        //user.setBirthDate(cursor.getString(4));
        user.setBirthDate(date);
        user.setLocation(cursor.getString(5));
        user.setUsername(cursor.getString(5));
        user.setPassword(cursor.getString(5));

        return user;
    }


}