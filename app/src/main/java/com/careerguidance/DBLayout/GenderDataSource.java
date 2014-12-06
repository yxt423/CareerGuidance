package com.careerguidance.DBLayout;

/**
 * Created by chris on 11/8/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.careerguidance.model.Gender;

import java.util.ArrayList;
import java.util.List;

public class GenderDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_GENDER_COLS[0][0],
            SQLiteHelperClass.TBL_GENDER_COLS[1][0]};

    public GenderDataSource(Context context)
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

    public boolean isValidGender(String strGender)
    {
        if (getGenderId(strGender) != -1)
            return true;
        else
            return false;
    }

    public int getGenderId(String name)
    {
        int genderId = -1;

        Cursor cursor = database.query(SQLiteHelperClass.TBL_GENDER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Gender gender = cursorToGender(cursor);

            if (name.equalsIgnoreCase(gender.getName()))
                genderId = gender.getId();

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return genderId;
    }

    public String getGenderName(int id)
    {
        String genderName = "";

        Cursor cursor = database.query(SQLiteHelperClass.TBL_GENDER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Gender gender = cursorToGender(cursor);

            if (id == gender.getId())
                genderName = gender.getName();

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return genderName;
    }

    public Gender createGender(String name)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_GENDER_COLS[1][0], name);

        long insertId = database.insert(SQLiteHelperClass.TBL_GENDER, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_GENDER,
                allColumns, SQLiteHelperClass.TBL_GENDER_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Gender newGender = cursorToGender(cursor);

        cursor.close();

        return newGender;
    }

    public void delete(Gender gender)
    {
        long id = gender.getId();

        database.delete(SQLiteHelperClass.TBL_GENDER, SQLiteHelperClass.TBL_GENDER_COLS[0][0]
                + " = " + id, null);
    }

    public List<Gender> getAllGenders()
    {
        List<Gender> genders = new ArrayList<Gender>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_GENDER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Gender gender = cursorToGender(cursor);
            genders.add(gender);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return genders;
    }

    private Gender cursorToGender(Cursor cursor) {
        Gender gender = new Gender();

        //gender.setId(cursor.getLong(0));

        gender.setName(cursor.getString(1));

        return gender;
    }


}