package com.careerguidance.dblayout;

/**
 * Created by chris on 11/8/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.careerguidance.cgexception.CGException;
import com.careerguidance.model.Grade;

import java.util.ArrayList;
import java.util.List;

public class User_GradeDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0],
            SQLiteHelperClass.TBL_USER_GRADE_COLS[1][0], SQLiteHelperClass.TBL_USER_GRADE_COLS[2][0]};

    public User_GradeDataSource(Context context)
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

    public Grade create_UserGrade(int userId, int subjectId, double GPA)
    {
        Grade newUser_Grade = null;

        //query the database to see if the record already exists
        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER_GRADE,
                allColumns, SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0] + " = " + userId
                        + " AND " + SQLiteHelperClass.TBL_USER_GRADE_COLS[1][0] + " = " + subjectId,
                null, null, null, null);



        if (cursor.getCount() == 0) //if the record doesn't already exist
        try
        {
            ContentValues values = new ContentValues();

            values.put(SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0], userId);

            values.put(SQLiteHelperClass.TBL_USER_GRADE_COLS[1][0], subjectId);

            values.put(SQLiteHelperClass.TBL_USER_GRADE_COLS[2][0], GPA);


            long insertId = database.insert(SQLiteHelperClass.TBL_USER_GRADE, null, //try to insert the record
                    values);

            //query the database to ensure the record was inserted
            cursor = database.query(SQLiteHelperClass.TBL_USER_GRADE,
                    allColumns, SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0] + " = " + insertId
                            + " AND " + SQLiteHelperClass.TBL_USER_GRADE_COLS[1][0] + " = " + subjectId,
                    null, null, null, null);

            //if it was inserted create a new object
            if (cursor.moveToFirst())
                newUser_Grade = cursorToUser_Grade(cursor);

            cursor.close(); //close the cursor
        }
        catch (SQLException sqlException) //if an error occurred when inserting
        {
            try
            {
                if(sqlException instanceof SQLiteConstraintException)
                {
                    try
                    {
                        CGException exception = new CGException("Foreign key constraint violation");

                        throw (exception);
                    }
                    catch (CGException e)
                    {

                    }
                }
            }
            catch (SQLException e)
            {

            }
        }

        //return the object
        return newUser_Grade;
    }

    public void addGrade(int userId, int subjectId, double GPA)
    {
        create_UserGrade(userId, subjectId, GPA);
    }

    public void deleteGrade(int userId, int subjectId)
    {
        database.delete(SQLiteHelperClass.TBL_USER_GRADE, SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0]
                + " = " + userId + " AND " + SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0] + " = " + subjectId, null);
    }

    public boolean update(int userId, int subjectId, double gpa)
    {
        int rowsAffected = 0;

        ContentValues values = new ContentValues();

        values.put("subject_id", subjectId);
        values.put("gpa", gpa);

        rowsAffected = database.update(SQLiteHelperClass.TBL_USER_GRADE, values, "user_id = " + userId, null);

        if (rowsAffected == 1)
            return true;
        else
            return false;
    }


    public List<Grade> getAllUser_Grades()
    {
        List<Grade> user_Grades = new ArrayList<Grade>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER_GRADE,
                allColumns, "user_id = 1", null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Grade user_Grade = cursorToUser_Grade(cursor);
            user_Grades.add(user_Grade);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return user_Grades;
    }

    private Grade cursorToUser_Grade(Cursor cursor) {
        Grade user_Grade = new Grade();

        //user_Grade.setId(cursor.getLong(0));

        user_Grade.setSubject(cursor.getString(1));

        user_Grade.setGPA(cursor.getDouble(2));

        return user_Grade;
    }


}