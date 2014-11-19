package com.careerguidance.DBLayout;

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
import java.util.Date;
import java.util.List;

public class CareerDataSource
{

    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.COLUMN_CAREER_ID[0],
            SQLiteHelperClass.COLUMN_CAREER1[0], SQLiteHelperClass.COLUMN_CAREER2[0],
            SQLiteHelperClass.COLUMN_CAREER3[0]};

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
        values.put(SQLiteHelperClass.COLUMN_CAREER1[0], name);
        values.put(SQLiteHelperClass.COLUMN_CAREER2[0], description);
        values.put(SQLiteHelperClass.COLUMN_CAREER3[0], salary);


        long insertId = database.insert(SQLiteHelperClass.TABLE_CAREER, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TABLE_CAREER,
                allColumns, SQLiteHelperClass.COLUMN_CAREER_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Career newCareer = cursorToCareer(cursor);

        cursor.close();

        return newCareer;
    }

    public void delete(Career career)
    {
        long id = career.getId();

        database.delete(SQLiteHelperClass.TABLE_CAREER, SQLiteHelperClass.COLUMN_CAREER_ID
                + " = " + id, null);
    }

    public List<Career> getAllCareers()
    {
        List<Career> careers = new ArrayList<Career>();

        Cursor cursor = database.query(SQLiteHelperClass.TABLE_CAREER,
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

    private Career cursorToCareer(Cursor cursor) {
        Career career = new Career();

        //career.setId(cursor.getLong(0));

        career.setName(cursor.getString(1));
        career.setDescription(cursor.getString(2));
        career.setAvgSalary(cursor.getDouble(3));

        return career;
    }


}