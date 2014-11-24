package com.careerguidance.DBLayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.careerguidance.model.University;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 11/8/14.
 */
public class UniversityDataSource
{

    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_UNIVERSITY_COLS[0][0], SQLiteHelperClass.TBL_UNIVERSITY_COLS[1][0],
            SQLiteHelperClass.TBL_UNIVERSITY_COLS[2][0], SQLiteHelperClass.TBL_UNIVERSITY_COLS[3][0],
            SQLiteHelperClass.TBL_UNIVERSITY_COLS[4][0], SQLiteHelperClass.TBL_UNIVERSITY_COLS[5][0]};

    public UniversityDataSource(Context context)
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

    public University createUniversity(String name, String description, double fees, String url, String location)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_COLS[1][0], name);
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_COLS[2][0], description);
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_COLS[3][0], fees);
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_COLS[4][0], url);
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_COLS[5][0], location);


        long insertId = database.insert(SQLiteHelperClass.TBL_USER, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_UNIVERSITY,
                allColumns, SQLiteHelperClass.TBL_UNIVERSITY_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        University newUniversity = cursorToUniversity(cursor);

        cursor.close();

        return newUniversity;
    }

    public void delete(University university)
    {
        long id = university.getId();

        database.delete(SQLiteHelperClass.TBL_UNIVERSITY, SQLiteHelperClass.TBL_UNIVERSITY_COLS[0][0]
                + " = " + id, null);
    }

    public List<University> getAllUniversity()
    {
        List<University> universityList = new ArrayList<University>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_UNIVERSITY,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            University university = cursorToUniversity(cursor);
            universityList.add(university);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return universityList;
    }

    public List<String> getAllUniversityNames()
    {
        List<String> universityNameList = new ArrayList<String>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_UNIVERSITY,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            University university = cursorToUniversity(cursor);
            universityNameList.add(university.getName());
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return universityNameList;
    }
    private University cursorToUniversity(Cursor cursor) {
        University university = new University();

        //university.setId(cursor.getLong(0));

        university.setName(cursor.getString(1));
        university.setDescription(cursor.getString(2));
        university.setFees(cursor.getDouble(3));

        return university;
    }


}