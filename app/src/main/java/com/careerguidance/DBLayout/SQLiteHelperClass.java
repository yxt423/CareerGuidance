package com.careerguidance.DBLayout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.database.MatrixCursor;

import java.util.ArrayList;

/**
 * Created by chris on 11/8/14.
 */

public class SQLiteHelperClass extends SQLiteOpenHelper
{

    protected static final String DATABASE_NAME = "careerguidance.db";

    protected static final int DATABASE_VERSION = 1;

    //Location Table================
    protected static final String TBL_LOCATION = "location";

    protected static final String TBL_LOCATION_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                        {"name", "TEXT"},
                                                        {"currency", "TEXT"},
                                                        {"currency_symbol","TEXT"}};

    protected static final String  TBL_LOCATION_OPTIONS [] = {};

    //Gender Table================
    protected static final String TBL_GENDER = "gender";

    protected static final String TBL_GENDER_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                        {"name", "TEXT"}};

    protected static final String  TBL_GENDER_OPTIONS [] = {};

    //Subjects Table================
    protected static final String TBL_SUBJECT = "subject";

    protected static final String TBL_SUBJECT_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                        {"name", "TEXT"},
                                                        {"description", "TEXT"}};

    protected static final String  TBL_SUBJECT_OPTIONS [] = {};

    //Interests Table===============
    protected static final String TBL_INTEREST = "interest";

    protected static final String TBL_INTEREST_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                        {"name", "TEXT"},
                                                        {"description", "TEXT"}};

    protected static final String  TBL_INTEREST_OPTIONS [] = {};

    //Career Table==================
    protected static final String TBL_CAREER = "career";

    protected static final String TBL_CAREER_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                        {"name", "TEXT"},
                                                        {"description", "TEXT"},
                                                        {"salary", "REAL"}};

    protected static final String  TBL_CAREER_OPTIONS [] = {};

    //University Table==============
    protected static final String TBL_UNIVERSITY = "university";

    protected static final String TBL_UNIVERSITY_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                            {"name", "TEXT"},
                                                            {"description", "TEXT"},
                                                            {"fees", "REAL"},
                                                            {"url", "TEXT"},
                                                            {"location_id", "INTEGER"}};


    protected static final String  TBL_UNIVERSITY_OPTIONS [] = {"FOREIGN KEY (location_id) REFERENCES location(_id)"};

    //University_program Table=====================
    protected static final String TBL_UNIVERSITY_PROGRAM = "university_program";

    protected static final String TBL_UNIVERSITY_PROGRAM_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                                    {"university_id", "INTEGER"},
                                                                    {"name", "INTEGER"},
                                                                    {"career_id", "INTEGER"},
                                                                    {"description", "TEXT"},
                                                                    {"gpa", "REAL"}};

    protected static final String  TBL_UNIVERSITY_PROGRAM_OPTIONS [] = {"FOREIGN KEY (university_id) REFERENCES university(_id)",
                                                                    "FOREIGN KEY (career_id) REFERENCES career(_id)"};

    //User Table=====================
    protected static final String TBL_USER = "user";

    protected static final String  TBL_USER_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                        {"firstname", "TEXT"},
                                                        {"lastname", "TEXT"},
                                                        {"gender_id", "INTEGER"},
                                                        {"birthdate", "TEXT"},
                                                        {"location_id", "INTEGER"},
                                                        {"username", "TEXT"},
                                                        {"password", "TEXT"},
                                                        {"career_id", "INTEGER"},
                                                        {"university_id", "INTEGER"}};

    protected static final String  TBL_USER_OPTIONS [] = {"FOREIGN KEY (gender_id) REFERENCES gender(_id)",
                                                        "FOREIGN KEY (location_id) REFERENCES location(_id)",
                                                        "FOREIGN KEY (career_id) REFERENCES career(_id)",
                                                        "FOREIGN KEY (university_id) REFERENCES university(_id)"};

    //User_Grade Table===============
    protected static final String TBL_USER_GRADE = "user_grade";

    protected static final String TBL_USER_GRADE_COLS [][] = {{"user_id", "INTEGER"},
                                                            {"subject_id", "INTEGER"},
                                                            {"gpa", "REAL"}};

    protected static final String  TBL_USER_GRADE_OPTIONS [] = {"FOREIGN KEY (user_id) REFERENCES user(_id)",
                                                            "FOREIGN KEY (subject_id) REFERENCES subject(_id)"};

    //User_Interest Table===============
    protected static final String TBL_USER_INTEREST = "user_interest";

    protected static final String TBL_USER_INTEREST_COLS [][] = {{"user_id", "INTEGER"},
                                                             {"interest_id", "INTEGER"}};

    protected static final String  TBL_USER_INTEREST_OPTIONS [] = {"FOREIGN KEY (user_id) REFERENCES user(_id)",
                                                                "FOREIGN KEY (interest_id) REFERENCES interest(_id)"};

    //University_Grade Table===============
    protected static final String TBL_UNIVERSITY_GRADE = "university_grade";

    protected static final String TBL_UNIVERSITY_GRADE_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                                {"university_id", "INTEGER"},
                                                                {"program_id", "INTEGER"},
                                                                {"subject_id", "INTEGER"},
                                                                {"gpa", "REAL"}};

    protected static final String  TBL_UNIVERSITY_GRADE_OPTIONS [] = {"FOREIGN KEY (university_id) REFERENCES university(_id)",
                                                                    "FOREIGN KEY (program_id) REFERENCES university_program(_id)",
                                                                    "FOREIGN KEY (subject_id) REFERENCES subject(_id)"};

    //University_Career Table===============
    protected static final String TBL_UNIVERSITY_CAREER = "university_career";

    protected static final String TBL_UNIVERSITY_CAREER_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                                {"university_id", "INTEGER"},
                                                                {"program_id", "INTEGER"},
                                                                {"career_id", "INTEGER"}};

    protected static final String  TBL_UNIVERSITY_CAREER_OPTIONS [] = {"FOREIGN KEY (university_id) REFERENCES university(_id)",
                                                                    "FOREIGN KEY (program_id) REFERENCES university_program(_id)",
                                                                    "FOREIGN KEY (career_id) REFERENCES career(_id)"};

    //University_Interest Table===============
    protected static final String TBL_UNIVERSITY_INTEREST = "university_interest";

    protected static final String TBL_UNIVERSITY_INTEREST_COLS [][] = {{"_id", "INTEGER PRIMARY KEY"},
                                                                    {"university_id", "INTEGER"},
                                                                    {"program_id", "INTEGER"},
                                                                    {"interest_id", "INTEGER"}};

    protected static final String  TBL_UNIVERSITY_INTEREST_OPTIONS [] = {"FOREIGN KEY (university_id) REFERENCES university(_id)",
                                                                        "FOREIGN KEY (program_id) REFERENCES university_program(_id)",
                                                                        "FOREIGN KEY (interest_id) REFERENCES interest(_id)"};

    //methods========================

    public void createTable(SQLiteDatabase database, String tblName, String [][] tblCols, String [] tblFKeys)
    {
        String sqlCreate = "CREATE TABLE " + tblName + "( ";

        for (int i = 0; i < tblCols.length; i++)
        {
            sqlCreate += tblCols[i][0] + " " + tblCols[i][1];

            if(i != tblCols.length - 1)
                sqlCreate += ", ";
        }

        String sqlFKeys = "";

        if (tblFKeys.length > 0)
            sqlFKeys = ", ";

        for (int i = 0; i < tblFKeys.length; i++)
        {
            sqlFKeys += tblFKeys[i];

            if(i != tblFKeys.length - 1)
                sqlFKeys += ", ";
        }

        sqlCreate += sqlFKeys + ");";

        Log.d("MYSQL:", sqlCreate);

        database.execSQL(sqlCreate);
    }

    public void populateTables(SQLiteDatabase database)
    {
        //location table
        ContentValues values = new ContentValues();
        values.put("name", "United States of America");
        values.put("currency", "USD");
        values.put("currency_symbol", "$");

        database.insert("location", null, values);

        values = null;
        values = new ContentValues();

        values.put("name", "China");
        values.put("currency", "YEN");
        values.put("currency_symbol", "¥");

        database.insert("location", null, values);

        values = null;
        values = new ContentValues();

        values.put("name", "Kenya");
        values.put("currency", "KES");
        values.put("currency_symbol", "K");

        database.insert("location", null, values);

        //gender table
        values = null;
        values = new ContentValues();

        values.put("name", "Female");

        database.insert("gender", null, values);

        values = null;
        values = new ContentValues();

        values.put("name", "Male");

        database.insert("gender", null, values);

        //subject table
        values = null;
        values = new ContentValues();

        values.put("name", "English");
        values.put("description", "English");

        database.insert("subject", null, values);

        values = null;
        values = new ContentValues();

        values.put("name", "Mathematics");
        values.put("description", "Mathematics");

        database.insert("subject", null, values);

        //interest table
        values = null;
        values = new ContentValues();

        values.put("name", "Music");

        database.insert("interest", null, values);

        values = null;
        values = new ContentValues();

        values.put("name", "Sports");

        database.insert("interest", null, values);

        //career table
        values = null;
        values = new ContentValues();

        values.put("name", "Medicine");
        values.put("description", "Be a doctor!");
        values.put("salary", "40000");

        database.insert("career", null, values);

        //university table
        values = null;
        values = new ContentValues();

        values.put("name", "Massachusetts Institute of Technology");
        values.put("description", "Massachusetts Institute of Technology");
        values.put("fees", "56000");
        values.put("url", "http://www.mit.edu");
        values.put("location_id", 1);

        database.insert("university", null, values);

        values = null;
        values = new ContentValues();

        values.put("name", "Carnegie Mellon University");
        values.put("description", "Carnegie Mellon University");
        values.put("fees", "40000");
        values.put("url", "http://www.cmu.edu");
        values.put("location_id", 1);

        database.insert("university", null, values);

        //university_program table
        values = null;
        values = new ContentValues();

        values.put("university_id", 1);
        values.put("name", "B.Sc. Computer Science");
        values.put("career_id", 1);
        values.put("description", "Computer Science");
        values.put("gpa", 3.4);

        database.insert("university_program", null, values);

        values = null;
        values = new ContentValues();

        values.put("university_id", 2);
        values.put("name", "B.Sc. Computer Science");
        values.put("career_id", 1);
        values.put("description", "Computer Science");
        values.put("gpa", 3.6);

        database.insert("university_program", null, values);

        //user table
        values = null;
        values = new ContentValues();

        values.putNull("firstname");
        values.putNull("lastname");
        values.putNull("gender_id");
        values.putNull("birthdate");
        values.putNull("location_id");
        values.putNull("username");
        values.putNull("password");
        values.putNull("career_id");
        values.putNull("university_id");

        database.insert("user", null, values);

        //university_grade table
        values = null;
        values = new ContentValues();

        values.put("university_id", 1);
        values.put("program_id", 1);
        values.put("subject_id", 1);
        values.put("gpa", 3.4);

        database.insert("university_grade", null, values);

        values = null;
        values = new ContentValues();

        values.put("university_id", 2);
        values.put("program_id", 1);
        values.put("subject_id", 1);
        values.put("gpa", 3.0);

        database.insert("university_grade", null, values);

        //user_grade table
/*        values = null;
        values = new ContentValues();

        values.put("user_id", 1);
        values.put("subject_id", 1);
        values.put("gpa", 3.4);

        database.insert("user_grade", null, values);

        values = null;
        values = new ContentValues();

        values.put("user_id", 1);
        values.put("subject_id", 2);
        values.put("gpa", 3.0);

        database.insert("user_grade", null, values);


        //user_interest table
        values = null;
        values = new ContentValues();

        values.put("user_id", 1);
        values.put("interest_id", 1);

        database.insert("user_interest", null, values);
*/
        //university_career table
        values = null;
        values = new ContentValues();

        values.put("university_id", 1);
        values.put("program_id", 1);
        values.put("career_id", 1);

        database.insert("university_career", null, values);

        //university_interest table
        values = null;
        values = new ContentValues();

        values.put("university_id", 1);
        values.put("program_id", 1);
        values.put("interest_id", 1);

        database.insert("university_interest", null, values);
    }

    public SQLiteHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

 /*
        database.execSQL("drop table location");
        database.execSQL("drop table gender");
        database.execSQL("drop table subject");
        database.execSQL("drop table interest");
        database.execSQL("drop table career");
        database.execSQL("drop table university");
        database.execSQL("drop table user");
        database.execSQL("drop table grade");
        database.execSQL("drop table university_grade");
        database.execSQL("drop table university_career");
        database.execSQL("drop table university_interest");
*/
        createTable(database, TBL_LOCATION, TBL_LOCATION_COLS, TBL_LOCATION_OPTIONS);

        createTable(database, TBL_GENDER, TBL_GENDER_COLS, TBL_GENDER_OPTIONS);

        createTable(database, TBL_SUBJECT, TBL_SUBJECT_COLS, TBL_SUBJECT_OPTIONS);

        createTable(database, TBL_INTEREST, TBL_INTEREST_COLS, TBL_INTEREST_OPTIONS);

        createTable(database, TBL_CAREER, TBL_CAREER_COLS, TBL_CAREER_OPTIONS);

        createTable(database, TBL_UNIVERSITY, TBL_UNIVERSITY_COLS, TBL_UNIVERSITY_OPTIONS);

        createTable(database, TBL_UNIVERSITY_PROGRAM, TBL_UNIVERSITY_PROGRAM_COLS, TBL_UNIVERSITY_PROGRAM_OPTIONS);

        createTable(database, TBL_USER, TBL_USER_COLS, TBL_USER_OPTIONS);

        createTable(database, TBL_USER_GRADE, TBL_USER_GRADE_COLS, TBL_USER_GRADE_OPTIONS);

        createTable(database, TBL_USER_INTEREST, TBL_USER_INTEREST_COLS, TBL_USER_INTEREST_OPTIONS);

        createTable(database, TBL_UNIVERSITY_GRADE, TBL_UNIVERSITY_GRADE_COLS, TBL_UNIVERSITY_GRADE_OPTIONS);

        createTable(database, TBL_UNIVERSITY_CAREER, TBL_UNIVERSITY_CAREER_COLS, TBL_UNIVERSITY_CAREER_OPTIONS);

        createTable(database, TBL_UNIVERSITY_INTEREST, TBL_UNIVERSITY_INTEREST_COLS, TBL_UNIVERSITY_INTEREST_OPTIONS);

        populateTables(database);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelperClass.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TBL_CAREER);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_UNIVERSITY);
        db.execSQL("DROP TABLE IF EXISTS " + TBL_USER);

        onCreate(db);
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);


        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);


            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {


                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){

            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }


    }
}

