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

import java.util.ArrayList;
import java.util.List;

public class CareerDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_CAREER_COLS[0][0],
            SQLiteHelperClass.TBL_CAREER_COLS[1][0], SQLiteHelperClass.TBL_CAREER_COLS[2][0],
            SQLiteHelperClass.TBL_CAREER_COLS[3][0]};

    public CareerDataSource(Context context)
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

    public Career createCareer(String name, String description, double salary)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_CAREER_COLS[1][0], name);
        values.put(SQLiteHelperClass.TBL_CAREER_COLS[2][0], description);
        values.put(SQLiteHelperClass.TBL_CAREER_COLS[3][0], salary);


        long insertId = database.insert(SQLiteHelperClass.TBL_CAREER, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_CAREER,
                allColumns, SQLiteHelperClass.TBL_CAREER_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Career newCareer = cursorToCareer(cursor);

        cursor.close();

        return newCareer;
    }

    public void delete(Career career)
    {
        long id = career.getId();

        database.delete(SQLiteHelperClass.TBL_CAREER, SQLiteHelperClass.TBL_CAREER_COLS[0][0]
                + " = " + id, null);
    }

    public ArrayList<Career> getAllCareers()
    {
        ArrayList<Career> careers = new ArrayList<Career>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_CAREER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Career career = cursorToCareer(cursor);
            careers.add(career);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return careers;
    }

    public ArrayList<String> getAllCareerNames()
    {
        ArrayList<String> careerNames = new ArrayList<String>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_CAREER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Career career = cursorToCareer(cursor);
            careerNames.add(career.getName());
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return careerNames;
    }

    public int getIdFromName(String careerName)
    {
        int careerId = -1;

        Cursor cursor = database.query(SQLiteHelperClass.TBL_CAREER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Career career = cursorToCareer(cursor);

            if (careerName.equals(career.getName()))
            {
                careerId = career.getId();

                break;
            }

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return careerId;
    }

    public String getNameFromId(int careerId)
    {
        String careerName = "";

        Cursor cursor = database.query(SQLiteHelperClass.TBL_CAREER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Career career = cursorToCareer(cursor);

            if (careerId == career.getId())
            {
                careerName = career.getName();

                break;
            }

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return careerName;
    }

    public Career getCareerById(int careerId)
    {
        Career career = null;

        Cursor cursor = database.query(SQLiteHelperClass.TBL_CAREER,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Career career = cursorToCareer(cursor);

            if (career.getId() == careerId)
            {
                return career;

                break;
            }

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return career;
    }

    private Career cursorToCareer(Cursor cursor) {
        Career career = new Career();

        //career.setId(cursor.getLong(0));

        career.setName(cursor.getString(1));
        career.setDescription(cursor.getString(2));
        career.setAvgSalary(cursor.getDouble(3));

        return career;
    }


}