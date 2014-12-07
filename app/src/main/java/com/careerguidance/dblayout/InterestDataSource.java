package com.careerguidance.dblayout;

/**
 * Created by chris on 11/8/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.careerguidance.model.Interest;

import java.util.ArrayList;
import java.util.List;

public class InterestDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_INTEREST_COLS[0][0],
            SQLiteHelperClass.TBL_INTEREST_COLS[1][0], SQLiteHelperClass.TBL_INTEREST_COLS[2][0]};

    public InterestDataSource(Context context)
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

    public Interest createInterest(String name, String description, double salary)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_INTEREST_COLS[1][0], name);
        values.put(SQLiteHelperClass.TBL_INTEREST_COLS[2][0], description);

        long insertId = database.insert(SQLiteHelperClass.TBL_INTEREST, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_INTEREST,
                allColumns, SQLiteHelperClass.TBL_INTEREST_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Interest newInterest = cursorToInterest(cursor);

        cursor.close();

        return newInterest;
    }

    public void delete(Interest interest)
    {
        long id = interest.getId();

        database.delete(SQLiteHelperClass.TBL_INTEREST, SQLiteHelperClass.TBL_INTEREST_COLS[0][0]
                + " = " + id, null);
    }

    public ArrayList<Interest> getAllInterests()
    {
        ArrayList<Interest> interests = new ArrayList<Interest>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_INTEREST,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Interest interest = cursorToInterest(cursor);
            interests.add(interest);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return interests;
    }

    public ArrayList<String> getAllInterestNames()
    {
        ArrayList<String> interestNameList = new ArrayList<String>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_INTEREST,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Interest interest = cursorToInterest(cursor);
            interestNameList.add(interest.getName());
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return  interestNameList;
    }

    public String getNameFromId(int interestId)
    {
        String interestName = "";

        Cursor cursor = database.query(SQLiteHelperClass.TBL_INTEREST,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Interest interest = cursorToInterest(cursor);

            if (interest.getId() == interestId)
            {
                interestName = interest.getName();

                break;
            }

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return  interestName;
    }

    public int getIdFromName (String interestName)
    {
        int interestId = -1;

        Cursor cursor = database.query(SQLiteHelperClass.TBL_INTEREST,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Interest interest = cursorToInterest(cursor);

            if (interestName.equals(interest.getName()))
            {
                interestId = interest.getId();

                break;
            }

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return  interestId;
    }

    private Interest cursorToInterest(Cursor cursor) {
        Interest interest = new Interest();

        //interest.setId(cursor.getLong(0));

        interest.setName(cursor.getString(1));
        interest.setDescription(cursor.getString(2));

        return interest;
    }


}