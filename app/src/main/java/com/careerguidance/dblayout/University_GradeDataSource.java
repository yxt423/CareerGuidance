package com.careerguidance.dblayout;

/**
 * Created by chris on 11/8/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.careerguidance.model.Grade;
import com.careerguidance.model.Subject;
import com.careerguidance.model.University;

import java.util.ArrayList;
import java.util.List;

public class University_GradeDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[0][0],
            SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[1][0], SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[2][0],
            SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[3][0]};

    public University_GradeDataSource(Context context)
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

    public Grade createUniversity_Grade(University university, Subject subject, double GPA)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[1][0], university.getId());
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[2][0], subject.getName());
        values.put(SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[3][0], GPA);


        long insertId = database.insert(SQLiteHelperClass.TBL_UNIVERSITY_GRADE, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_UNIVERSITY_GRADE,
                allColumns, SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Grade newUniversity_Grade = cursorToUniversity_Grade(cursor);

        cursor.close();

        return newUniversity_Grade;
    }

    public void add(int universityId, int subjectId)
    {
        database.insert(SQLiteHelperClass.TBL_UNIVERSITY_GRADE, SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[0][0]
                + " = " + universityId + " AND " + SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[0][0] + " = " + subjectId, null);
    }

    public void delete(int universityId, int subjectId)
    {
        database.delete(SQLiteHelperClass.TBL_UNIVERSITY_GRADE, SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[0][0]
                + " = " + universityId + " AND " + SQLiteHelperClass.TBL_UNIVERSITY_GRADE_COLS[0][0] + " = " + subjectId, null);
    }

    public boolean update(int universityId, int subjectId, double gpa)
    {
        int rowsAffected = 0;

        ContentValues values = new ContentValues();

        values.put("subject_id", subjectId);
        values.put("gpa", gpa);

        rowsAffected = database.update(SQLiteHelperClass.TBL_UNIVERSITY_GRADE, values, "university_id = " + universityId, null);

        if (rowsAffected == 1)
            return true;
        else
            return false;
    }


    public List<Grade> getAllUniversity_Grades(int university_id)
    {
        List<Grade> university_Grades = new ArrayList<Grade>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_UNIVERSITY_GRADE,
                allColumns, "university_id = " + university_id, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Grade university_Grade = cursorToUniversity_Grade(cursor);
            university_Grades.add(university_Grade);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return university_Grades;
    }

    private Grade cursorToUniversity_Grade(Cursor cursor) {
        Grade university_grade = new Grade();

        university_grade.setSubject(cursor.getString(1));
        university_grade.setGPA(cursor.getDouble(2));

        return university_grade;
    }


}