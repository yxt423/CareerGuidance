package com.careerguidance.DBLayout;

/**
 * Created by chris on 11/8/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.careerguidance.model.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_SUBJECT_COLS[0][0],
            SQLiteHelperClass.TBL_SUBJECT_COLS[1][0], SQLiteHelperClass.TBL_SUBJECT_COLS[2][0],
            SQLiteHelperClass.TBL_SUBJECT_COLS[3][0]};

    public SubjectDataSource(Context context)
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

    public Subject createSubject(String name, String description, double salary)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_SUBJECT_COLS[1][0], name);
        values.put(SQLiteHelperClass.TBL_SUBJECT_COLS[2][0], description);
        values.put(SQLiteHelperClass.TBL_SUBJECT_COLS[3][0], salary);


        long insertId = database.insert(SQLiteHelperClass.TBL_SUBJECT, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_SUBJECT,
                allColumns, SQLiteHelperClass.TBL_SUBJECT_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Subject newSubject = cursorToSubject(cursor);

        cursor.close();

        return newSubject;
    }

    public void delete(Subject subject)
    {
        long id = subject.getId();

        database.delete(SQLiteHelperClass.TBL_SUBJECT, SQLiteHelperClass.TBL_SUBJECT_COLS[0][0]
                + " = " + id, null);
    }

    public List<Subject> getAllSubjects()
    {
        List<Subject> subjects = new ArrayList<Subject>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_SUBJECT,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Subject subject = cursorToSubject(cursor);
            subjects.add(subject);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return subjects;
    }

    private Subject cursorToSubject(Cursor cursor) {
        Subject subject = new Subject();

        //subject.setId(cursor.getLong(0));

        subject.setName(cursor.getString(1));
        subject.setDescription(cursor.getString(2));

        return subject;
    }


}