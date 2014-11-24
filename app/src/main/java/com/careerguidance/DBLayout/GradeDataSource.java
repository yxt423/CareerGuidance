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
import com.careerguidance.model.User;
import com.careerguidance.model.User_Grade;

import java.util.ArrayList;
import java.util.List;

public class GradeDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0],
            SQLiteHelperClass.TBL_USER_GRADE_COLS[1][0], SQLiteHelperClass.TBL_USER_GRADE_COLS[2][0],
            SQLiteHelperClass.TBL_USER_GRADE_COLS[3][0]};

    public GradeDataSource(Context context)
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

    public User_Grade createUser_Grade(User user, Subject subject, double GPA)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_USER_GRADE_COLS[1][0], user.getId());
        values.put(SQLiteHelperClass.TBL_USER_GRADE_COLS[2][0], subject.getName());
        values.put(SQLiteHelperClass.TBL_USER_GRADE_COLS[3][0], GPA);


        long insertId = database.insert(SQLiteHelperClass.TBL_USER_GRADE, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER_GRADE,
                allColumns, SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        User_Grade newUser_Grade = cursorToUser_Grade(cursor);

        cursor.close();

        return newUser_Grade;
    }

    public void delete(User_Grade user_Grade)
    {
        long id = user_Grade.getId();

        database.delete(SQLiteHelperClass.TBL_USER_GRADE, SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0]
                + " = " + id, null);
    }

    public List<User_Grade> getAllUser_Grades()
    {
        List<User_Grade> user_Grades = new ArrayList<User_Grade>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER_GRADE,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            User_Grade user_Grade = cursorToUser_Grade(cursor);
            user_Grades.add(user_Grade);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return user_Grades;
    }

    private User_Grade cursorToUser_Grade(Cursor cursor) {
        User_Grade user_Grade = new User_Grade();

        //user_Grade.setId(cursor.getLong(0));

        user_Grade.setSubject(cursor.getString(1));
        user_Grade.setGPA(cursor.getDouble(2));

        return user_Grade;
    }


}