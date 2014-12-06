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
import com.careerguidance.model.Subject;
import com.careerguidance.model.University;

import java.util.ArrayList;
import java.util.List;

public class University_InterestDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[0][0],
            SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[1][0], SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[2][0]};

    public University_InterestDataSource(Context context)
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

    public Interest createUniversity_Interest(University university, Subject subject, double GPA)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[1][0], university.getId());
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[2][0], subject.getName());

        long insertId = database.insert(SQLiteHelperClass.TBL_UNIVERSITY_INTEREST, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_UNIVERSITY_INTEREST,
                allColumns, SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Interest newUniversity_Interest = cursorToUniversity_Interest(cursor);

        cursor.close();

        return newUniversity_Interest;
    }

    public void add(int universityId, int subjectId)
    {
        database.insert(SQLiteHelperClass.TBL_UNIVERSITY_INTEREST, SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[0][0]
                + " = " + universityId + " AND " + SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[0][0] + " = " + subjectId, null);
    }

    public void delete(int universityId, int subjectId)
    {
        database.delete(SQLiteHelperClass.TBL_UNIVERSITY_INTEREST, SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[0][0]
                + " = " + universityId + " AND " + SQLiteHelperClass.TBL_UNIVERSITY_INTEREST_COLS[0][0] + " = " + subjectId, null);
    }

    public boolean update(int universityId, int subjectId, double gpa)
    {
        int rowsAffected = 0;

        ContentValues values = new ContentValues();

        values.put("subject_id", subjectId);
        values.put("gpa", gpa);

        rowsAffected = database.update(SQLiteHelperClass.TBL_UNIVERSITY_INTEREST, values, "university_id = " + universityId, null);

        if (rowsAffected == 1)
            return true;
        else
            return false;
    }

    public List<Interest> getAllUniversity_Interests()
    {
        List<Interest> university_Interests = new ArrayList<Interest>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_UNIVERSITY_INTEREST,
                allColumns, "university_id = 1", null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Interest university_Interest = cursorToUniversity_Interest(cursor);
            university_Interests.add(university_Interest);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return university_Interests;
    }

    private Interest cursorToUniversity_Interest(Cursor cursor)
    {
        Interest university_Interest = new Interest();

        //university_Interest.setId(cursor.getLong(0));

        university_Interest.setName(cursor.getString(1));

        return university_Interest;
    }


}