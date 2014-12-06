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

import java.util.ArrayList;
import java.util.List;

public class User_InterestDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0],
            SQLiteHelperClass.TBL_USER_GRADE_COLS[1][0], SQLiteHelperClass.TBL_USER_GRADE_COLS[2][0]};

    public User_InterestDataSource(Context context)
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

    public Interest addInterest(int userId, int interestId)
    {
        Interest newInterest = null;

        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER_GRADE,
                allColumns, SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0] + " = " + interestId, null,
                null, null, null);

        if (cursor.getCount() == 0)
        {
            ContentValues values = new ContentValues();

            values.put(SQLiteHelperClass.TBL_USER_GRADE_COLS[1][0], userId);
            values.put(SQLiteHelperClass.TBL_USER_GRADE_COLS[2][0], interestId);

            long insertId = database.insert(SQLiteHelperClass.TBL_USER_GRADE, null, values);

            cursor = database.query(SQLiteHelperClass.TBL_USER_GRADE,
                    allColumns, SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0] + " = " + insertId, null,
                    null, null, null);

            cursor.moveToFirst();

            newInterest = cursorToUser_Interest(cursor);

            cursor.close();
        }

        return newInterest;
    }

    public void deleteInterest(int userId, int interestId)
    {
        database.delete(SQLiteHelperClass.TBL_USER_GRADE, SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0]
                + " = " + userId + " AND " + SQLiteHelperClass.TBL_USER_GRADE_COLS[0][0] + " = " + interestId, null);
    }

    public List<Interest> getAllUser_Interests()
    {
        List<Interest> user_Interests = new ArrayList<Interest>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_USER_GRADE,
                allColumns, "user_id = 1", null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Interest user_Interest = cursorToUser_Interest(cursor);
            user_Interests.add(user_Interest);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return user_Interests;
    }

    private Interest cursorToUser_Interest(Cursor cursor)
    {
        Interest user_Interest = new Interest();

        //user_Interest.setId(cursor.getLong(0));

        user_Interest.setName(cursor.getString(1));

        return user_Interest;
    }


}