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
import com.careerguidance.model.University;

import java.util.ArrayList;
import java.util.List;

public class UniversityDataSource
{

    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.COLUMN_UNIVERSITY_ID[0],
            SQLiteHelperClass.COLUMN_UNIVERSITY1[0], SQLiteHelperClass.COLUMN_UNIVERSITY2[0],
            SQLiteHelperClass.COLUMN_UNIVERSITY3[0]};

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

    public University createUniversity(String name, String description, double salary)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.COLUMN_UNIVERSITY1[0], name);
        values.put(SQLiteHelperClass.COLUMN_UNIVERSITY2[0], description);
        values.put(SQLiteHelperClass.COLUMN_UNIVERSITY3[0], salary);


        long insertId = database.insert(SQLiteHelperClass.TABLE_UNIVERSITY, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TABLE_UNIVERSITY,
                allColumns, SQLiteHelperClass.COLUMN_UNIVERSITY_ID + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        University newUniversity = cursorToUniversity(cursor);

        cursor.close();

        return newUniversity;
    }

    public void delete(University university)
    {
        long id = university.getId();

        database.delete(SQLiteHelperClass.TABLE_UNIVERSITY, SQLiteHelperClass.COLUMN_UNIVERSITY_ID
                + " = " + id, null);
    }

    public List<University> getAllUniversitys()
    {
        List<University> universitys = new ArrayList<University>();

        Cursor cursor = database.query(SQLiteHelperClass.TABLE_UNIVERSITY,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            University university = cursorToUniversity(cursor);
            universitys.add(university);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return universitys;
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