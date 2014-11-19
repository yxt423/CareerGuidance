package com.careerguidance.DBLayout;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.careerguidance.model.*;

public class SQLiteHelperClass extends SQLiteOpenHelper {
    /**
     * Created by chris on 11/8/14.
     */

    private static final String DATABASE_NAME = "careerguidance.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID [] = {"_id", "integer primary key"};
    public static final String COLUMN_USER1 [] = {"firstname", "TEXT"};
    public static final String COLUMN_USER2 [] = {"lastname", "TEXT"};
    public static final String COLUMN_USER3 [] = {"gender", "TEXT"};
    public static final String COLUMN_USER4 [] = {"dateofbirth", "TEXT"};
    public static final String COLUMN_USER5 [] = {"location", "TEXT"};
    public static final String COLUMN_USER6 [] = {"username", "TEXT"};
    public static final String COLUMN_USER7 [] = {"password", "TEXT"};


    public SQLiteHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        TblUser.create(database);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(SQLiteHelperClass.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }

    public class TblUser
    {
        SQLiteDatabase sqliteDB;


        public static void create(SQLiteDatabase sqliteDB)
        {
            // Database creation sql statement
            String TBL_USER_CREATE = "CREATE TABLE "
                    + "user ( "
                    + "_id integer primary key, "
                    + "firstname text, "
                    + "lastname text, "
                    + "gender text, "
                    + "dateofbirth date, "
                    + "country text, "
                    + "username text, "
                    + "password text);";

            sqliteDB.execSQL(TBL_USER_CREATE);
        }

        public String deleteUser(SQLiteDatabase sqliteDB, int userId){
            String TBL_USER_DELETE = "DELETE FROM user WHERE _id = '" + userId + ";";

            sqliteDB.execSQL(TBL_USER_DELETE);
        }

        public String deleteAll(SQLiteDatabase sqliteDB){
            String TBL_USER_DELETE_ALL = "DELETE * FROM user;";

            sqliteDB.execSQL(TBL_USER_DELETE_ALL);
        }

        public User getUser(SQLiteDatabase sqliteDB, int userId){
            User user = new User();

            String TBL_USER_SELECT= "SELECT * FROM user WHERE _id = " + userId + ";";

            Cursor cursor = sqliteDB.rawQuery(TBL_USER_SELECT);

            if (cursor.moveToFirst())
            {
                user.setFirstName(cursor.getColumnName("firstname"));
                user.setLastName(cursor.getColumnName("lastname"));
                user.setBirthDate(cursor.getColumnName("birthdate"));
                user.setGender(cursor.getColumnName("gender"));
                user.setCareerChoice(cursor.getColumnName("careerchoice"));
                user.setUniversityChoice(cursor.getColumnName("universitychoice"));
                user.setUsername(cursor.getColumnName("username"));
                user.setPassword(cursor.getColumnName("password"));
            }

        }


        public void updateUser(SQLiteDatabase sqliteDB, int userId, String field, Object value){
            String TBL_USER_UPDATE = "UPDATE TABLE user SET " + field + " = " + (String)Object + " WHERE _id = '" + userId + ";";

            sqliteDB.execSQL(TBL_USER_UPDATE);
        }

    }

    public class TblCareer
    {
        SQLiteDatabase sqliteDB;


        public static void create(SQLiteDatabase sqliteDB)
        {
            // Database creation sql statement
            String TBL_CAREER_CREATE = "CREATE TABLE "
                    + "career ( "
                    + "_id integer PRIMARY KEY, "
                    + "name TEXT, "
                    + "description TEXT, "
                    + "salary real");";

            sqliteDB.execSQL(TBL_CAREER_CREATE);
        }

        public String deleteCareer(SQLiteDatabase sqliteDB, int careerId){
            String TBL_CAREER_DELETE = "DELETE FROM career WHERE _id = '" + careerId + ";";

            sqliteDB.execSQL(TBL_CAREER_DELETE);
        }

        public String deleteAll(SQLiteDatabase sqliteDB){
            String TBL_CAREER_DELETE_ALL = "DELETE * FROM career;";

            sqliteDB.execSQL(TBL_CAREER_DELETE_ALL);
        }

        public User getUser(SQLiteDatabase sqliteDB, int userId){
            User user = new User();

            String TBL_CAREER_SELECT= "SELECT * FROM career WHERE _id = " + userId + ";";

            Cursor cursor = sqliteDB.rawQuery(TBL_CAREER_SELECT);

            if (cursor.moveToFirst())
            {
                user.setFirstName(cursor.getColumnName("name"));
                user.setLastName(cursor.getColumnName("description"));
                user.setBirthDate(cursor.getColumnName("salary"));
            }

        }

        public void updateCareer(SQLiteDatabase sqliteDB, int careerId, String field, Object value){
            String TBL_CAREER_UPDATE = "UPDATE TABLE career SET " + field + " = " + (String)Object + " WHERE _id = " + careerId + ";";

            sqliteDB.execSQL(TBL_CAREER_UPDATE);
        }

    }

    public class TblUniversity
    {
        SQLiteDatabase sqliteDB;


        public static void create(SQLiteDatabase sqliteDB)
        {
            // Database creation sql statement
            String TBL_UNIVERSITY_CREATE = "CREATE TABLE "
                    + "university ( "
                    + "_id integer PRIMARY KEY, "
                    + "name TEXT, "
                    + "description TEXT,"
                    + "fees REAL,"
                    + "location TEXT,"
                    + "url TEXT"
                    +);";

            sqliteDB.execSQL(TBL_UNIVERSITY_CREATE);
        }

        public String delete(SQLiteDatabase sqliteDB, int universityId){
            String TBL_UNIVERSITY_DELETE = "DELETE * FROM university WHERE _id = '" + universityId + ";";

            sqliteDB.execSQL(TBL_UNIVERSITY_DELETE);
        }

        public String deleteAll(SQLiteDatabase sqliteDB){
            String TBL_UNIVERSITY_DELETE_ALL = "DELETE * FROM university;";

            sqliteDB.execSQL(TBL_UNIVERSITY_DELETE_ALL);
        }

        public User get(SQLiteDatabase sqliteDB, int universityId){
            University university = new University();

            String TBL_UNIVERSITY_SELECT= "SELECT * FROM university WHERE _id = " + universityId + ";";

            Cursor cursor = sqliteDB.rawQuery(TBL_UNIVERSITY_SELECT);

            if (cursor.moveToFirst())
            {
                university.(cursor.getColumnName("name"));
                university.setLastName(cursor.getColumnName("description"));
                university.setBirthDate(cursor.getColumnName("fees"));
                university.setGender(cursor.getColumnName("location"));
                university.setGender(cursor.getColumnName("url"));
            }

        }

        public void updateUser(SQLiteDatabase sqliteDB, int userId, String field, Object value){
            String TBL_UNIVERSITY_UPDATE = "UPDATE TABLE user SET " + field + " = " + (String)Object + " WHERE _id = " + userId + ";";

            sqliteDB.execSQL(TBL_UNIVERSITY_UPDATE);
        }

    }
}

