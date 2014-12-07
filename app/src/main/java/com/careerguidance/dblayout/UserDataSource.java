package com.careerguidance.dblayout;

/**
 * Created by chris on 11/8/14.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.careerguidance.model.Career;
import com.careerguidance.model.Location;
import com.careerguidance.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_USER_COLS[0][0],
            SQLiteHelperClass.TBL_USER_COLS[1][0], SQLiteHelperClass.TBL_USER_COLS[2][0],
            SQLiteHelperClass.TBL_USER_COLS[3][0], SQLiteHelperClass.TBL_USER_COLS[4][0],
            SQLiteHelperClass.TBL_USER_COLS[5][0], SQLiteHelperClass.TBL_USER_COLS[6][0],
            SQLiteHelperClass.TBL_USER_COLS[7][0]};

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
        values.put(SQLiteHelperClass.TBL_USER_COLS[1][0], fName);
        values.put(SQLiteHelperClass.TBL_USER_COLS[2][0], lName);
        values.put(SQLiteHelperClass.TBL_USER_COLS[3][0], gndr);
        values.put(SQLiteHelperClass.TBL_USER_COLS[4][0], dob.toString());
        values.put(SQLiteHelperClass.TBL_USER_COLS[5][0], location);
        values.put(SQLiteHelperClass.TBL_USER_COLS[6][0], username);
        values.put(SQLiteHelperClass.TBL_USER_COLS[7][0], password);

        long insertId = database.insert(SQLiteHelperClass.TBL_USER, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER,
                allColumns, SQLiteHelperClass.TBL_USER_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        User newUser = cursorToUser(cursor);

        cursor.close();

        return newUser;
    }

    public User updateUser(String fName, String lName, String gndr, Date dob, String location, String username, String password)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_USER_COLS[1][0], fName);
        values.put(SQLiteHelperClass.TBL_USER_COLS[2][0], lName);
        values.put(SQLiteHelperClass.TBL_USER_COLS[3][0], gndr);
        values.put(SQLiteHelperClass.TBL_USER_COLS[4][0], dob.toString());
        values.put(SQLiteHelperClass.TBL_USER_COLS[5][0], location);
        values.put(SQLiteHelperClass.TBL_USER_COLS[6][0], username);
        values.put(SQLiteHelperClass.TBL_USER_COLS[7][0], password);

        long insertId = database.update(SQLiteHelperClass.TBL_USER, values, "_id = 1", null);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER,
                allColumns, SQLiteHelperClass.TBL_USER_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        User newUser = cursorToUser(cursor);

        cursor.close();

        return newUser;
    }


    public void delete(User user)
    {
        long id = user.getId();

        database.delete(SQLiteHelperClass.TBL_USER, SQLiteHelperClass.TBL_USER_COLS[0][0]
                + " = " + id, null);
    }

    public User getUser(int userId)
    {
        User user = null;

        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER,allColumns, "_id = " + userId, null, null, null, null);

        if (cursor.moveToFirst())
            user = cursorToUser(cursor);

        // make sure to close the cursor
        cursor.close();

        return user;
    }

    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER,
                allColumns, "id = 1", null, null, null, null);

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

        user.setId(cursor.getInt(0));

        Date date = new Date();
        user.setFirstName(cursor.getString(1));
        user.setLastName(cursor.getString(2));
        user.setGender(cursor.getString(3));
        //user.setBirthDate(cursor.getString(4));
        user.setBirthDate(date);
        user.setLocation(cursor.getString(5));
        user.setUsername(cursor.getString(6));
        user.setPassword(cursor.getString(7));

        return user;
    }

    public boolean setUserDetail(int userId, String column, String strValue)
    {
        int rowsAffected = 0;

        ContentValues values = new ContentValues();

        values.put(column, strValue);

        rowsAffected = database.update("user", values, "_id = " + userId, null);

        if (rowsAffected == 1)
            return true;
        else
            return false;
    }

    public boolean setFirstName(int userId, String strFirstName)
    {
        return setUserDetail(userId, "firstname", strFirstName);
    }

    public boolean setLastName(int userId, String strLastName)
    {
        return setUserDetail(userId, "lastname", strLastName);
    }

    public boolean setLocation(int userId, int locationId)
    {
        int rowsAffected = 0;

        ContentValues values = new ContentValues();

        values.put("location_id", locationId);

        rowsAffected = database.update("user", values,"_id = " + userId, null);

        if (rowsAffected == 1)
            return true;
        else
            return false;
    }

    public boolean setGender(int userId, int genderId)
    {
        int rowsAffected = 0;

        ContentValues values = new ContentValues();

        values.put("gender_id", genderId);

        rowsAffected = database.update("user", values,"_id = " + userId, null);

        if (rowsAffected == 1)
            return true;
        else
            return false;
    }

    public boolean setUserName(int userId, String strUserName)
    {
        return setUserDetail(userId, "username", strUserName);
    }

    public boolean setPassword(int userId, String strPassword)
    {
        return setUserDetail(userId, "password", strPassword);
    }

    public boolean setUserUniversity(int userId, int university_id)
    {
        int rowsAffected = 0;

        ContentValues values = new ContentValues();

        values.put("university_id", university_id);

        rowsAffected = database.update("user", values, "_id = " + userId, null);

        if (rowsAffected == 1)
            return true;
        else
            return false;
    }

    public boolean setUserCareer(int userId, Career career)
    {
        int rowsAffected = 0;

        ContentValues values = new ContentValues();

        values.put("career_id", career.getId());

        rowsAffected = database.update("user", values, "_id = " + userId, null);

        if (rowsAffected == 1)
            return true;
        else
            return false;
    }

}