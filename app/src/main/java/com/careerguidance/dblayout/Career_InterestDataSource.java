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
import com.careerguidance.model.Interest;

import java.util.ArrayList;
import java.util.List;

public class Career_InterestDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[0][0],
            SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[1][0], SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[2][0]};

    public Career_InterestDataSource(Context context)
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

    public Interest createCareer_Interest(Career career, Interest interest, double GPA)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[1][0], career.getId());
        values.put(SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[2][0], interest.getName());

        long insertId = database.insert(SQLiteHelperClass.TBL_CAREER_INTEREST, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_CAREER_INTEREST,
                allColumns, SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Interest newCareer_Interest = cursorToCareer_Interest(cursor);

        cursor.close();

        return newCareer_Interest;
    }

    public void add(int careerId, int interestId)
    {
        database.insert(SQLiteHelperClass.TBL_CAREER_INTEREST, SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[0][0]
                + " = " + careerId + " AND " + SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[0][0] + " = " + interestId, null);
    }

    public void delete(int careerId, int interestId)
    {
        database.delete(SQLiteHelperClass.TBL_CAREER_INTEREST, SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[0][0]
                + " = " + careerId + " AND " + SQLiteHelperClass.TBL_CAREER_INTEREST_COLS[0][0] + " = " + interestId, null);
    }


    public List<Interest> getCareer_Interests(int careerId)
    {
        List<Interest> career_Interests = new ArrayList<Interest>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_CAREER_INTEREST,
                allColumns, "career_id = " + careerId, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Interest career_Interest = cursorToCareer_Interest(cursor);
            career_Interests.add(career_Interest);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return career_Interests;
    }

    public ArrayList<String> getCareer_InterestNames(int careerId)
    {
        ArrayList<String> interestNames = null;

        Cursor cursor = database.query(SQLiteHelperClass.TBL_CAREER_INTEREST,
                allColumns, "career_id = " + careerId, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Interest career_Interest = cursorToCareer_Interest(cursor);
            interestNames.add(career_Interest.getName());
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return interestNames;
    }

    private Interest cursorToCareer_Interest(Cursor cursor)
    {
        Interest career_Interest = new Interest();

        //career_Interest.setId(cursor.getLong(0));

        career_Interest.setName(cursor.getString(1));

        return career_Interest;
    }


}