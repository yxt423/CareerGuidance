package com.careerguidance.DBLayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by chris on 11/8/14.
 */
public class SQLiteHelperClass extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "careerguidance.db";
    private static final int DATABASE_VERSION = 1;

    //User Table
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID [] = {"_id", "integer primary key"};
    public static final String COLUMN_USER1 [] = {"firstname", "TEXT"};
    public static final String COLUMN_USER2 [] = {"lastname", "TEXT"};
    public static final String COLUMN_USER3 [] = {"gender", "TEXT"};
    public static final String COLUMN_USER4 [] = {"dateofbirth", "TEXT"};
    public static final String COLUMN_USER5 [] = {"location", "TEXT"};
    public static final String COLUMN_USER6 [] = {"username", "TEXT"};
    public static final String COLUMN_USER7 [] = {"password", "TEXT"};

    private static final String TBL_USER_CREATE = "create table "
            + TABLE_USER
            + "("
            + COLUMN_USER_ID[0] + " " + COLUMN_USER_ID[1] + ", "
            + COLUMN_USER1[0] + " " + COLUMN_USER1[1] + ", "
            + COLUMN_USER2[0] + " " + COLUMN_USER2[1] + ", "
            + COLUMN_USER3[0] + " " + COLUMN_USER3[1] + ", "
            + COLUMN_USER4[0] + " " + COLUMN_USER4[1] + ", "
            + COLUMN_USER5[0] + " " + COLUMN_USER5[1] + ", "
            + COLUMN_USER6[0] + " " + COLUMN_USER6[1] + ", "
            + COLUMN_USER7[0] + " " + COLUMN_USER7[1]
            + ");";

    //Career Table
    public static final String TABLE_CAREER = "user";
    public static final String COLUMN_CAREER_ID [] = {"_id", "integer primary key"};
    public static final String COLUMN_CAREER1 [] = {"name", "TEXT"};
    public static final String COLUMN_CAREER2 [] = {"description", "TEXT"};
    public static final String COLUMN_CAREER3 [] = {"salary", "REAL"};

    private static final String TBL_CAREER_CREATE = "create table "
            + TABLE_USER
            + "("
            + COLUMN_CAREER_ID[0] + " " + COLUMN_CAREER_ID[1] + ", "
            + COLUMN_CAREER1[0] + " " + COLUMN_CAREER1[1] + ", "
            + COLUMN_CAREER2[0] + " " + COLUMN_CAREER2[1] + ", "
            + COLUMN_CAREER3[0] + " " + COLUMN_CAREER3[1]
            + ");";

    //University Table
    public static final String TABLE_UNIVERSITY = "university";
    public static final String COLUMN_UNIVERSITY_ID [] = {"_id", "integer primary key"};
    public static final String COLUMN_UNIVERSITY1 [] = {"name", "TEXT"};
    public static final String COLUMN_UNIVERSITY2 [] = {"description", "TEXT"};
    public static final String COLUMN_UNIVERSITY3 [] = {"fees", "REAL"};
    public static final String COLUMN_UNIVERSITY4 [] = {"url", "TEXT"};
    public static final String COLUMN_UNIVERSITY5 [] = {"location", "TEXT"};

    private static final String TBL_UNIVERSITY_CREATE = "create table "
            + TABLE_USER
            + "("
            + COLUMN_UNIVERSITY_ID[0] + " " + COLUMN_CAREER_ID[1] + ", "
            + COLUMN_UNIVERSITY1[0] + " " + COLUMN_UNIVERSITY1[1] + ", "
            + COLUMN_UNIVERSITY2[0] + " " + COLUMN_UNIVERSITY2[1] + ", "
            + COLUMN_UNIVERSITY3[0] + " " + COLUMN_UNIVERSITY3[1] + ", "
            + COLUMN_UNIVERSITY4[0] + " " + COLUMN_UNIVERSITY4[1] + ", "
            + COLUMN_UNIVERSITY5[0] + " " + COLUMN_UNIVERSITY5[1]
            + ");";


    public SQLiteHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(TBL_USER_CREATE);

        database.execSQL(TBL_CAREER_CREATE);

        database.execSQL(TBL_UNIVERSITY_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelperClass.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }
}

