package com.careerguidance.dblayout;

/**
 * Created by chris on 11/8/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.careerguidance.model.Location;

import java.util.ArrayList;
import java.util.List;

public class LocationDataSource
{
    // Database fields
    private SQLiteDatabase database;

    private SQLiteHelperClass dbHelper;

    private String[] allColumns = { SQLiteHelperClass.TBL_LOCATION_COLS[0][0],
            SQLiteHelperClass.TBL_LOCATION_COLS[1][0], SQLiteHelperClass.TBL_LOCATION_COLS[2][0],
            SQLiteHelperClass.TBL_LOCATION_COLS[3][0]};

    public LocationDataSource(Context context)
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

    public Location createLocation(String name, String description, double salary)
    {
        ContentValues values = new ContentValues();
        values.put(SQLiteHelperClass.TBL_LOCATION_COLS[1][0], name);
        values.put(SQLiteHelperClass.TBL_LOCATION_COLS[2][0], description);
        values.put(SQLiteHelperClass.TBL_LOCATION_COLS[3][0], salary);


        long insertId = database.insert(SQLiteHelperClass.TBL_LOCATION, null,
                values);

        Cursor cursor = database.query(SQLiteHelperClass.TBL_LOCATION,
                allColumns, SQLiteHelperClass.TBL_LOCATION_COLS[0][0] + " = " + insertId, null,
                null, null, null);

        cursor.moveToFirst();

        Location newLocation = cursorToLocation(cursor);

        cursor.close();

        return newLocation;
    }

    public void delete(Location location)
    {
        long id = location.getId();

        database.delete(SQLiteHelperClass.TBL_LOCATION, SQLiteHelperClass.TBL_LOCATION_COLS[0][0]
                + " = " + id, null);
    }

    public List<Location> getAllLocations()
    {
        List<Location> locations = new ArrayList<Location>();

        Cursor cursor = database.query(SQLiteHelperClass.TBL_LOCATION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Location location = cursorToLocation(cursor);
            locations.add(location);
            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return locations;
    }

    public boolean isValidLocation(String strLocation)
    {
        if (getLocationId(strLocation) != -1)
            return true;
        else
            return false;
    }

    public int getLocationId(String name)
    {
        int locationId = -1;

        Cursor cursor = database.query(SQLiteHelperClass.TBL_LOCATION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Location location = cursorToLocation(cursor);

            if (name.equalsIgnoreCase(location.getName()))
                locationId = location.getId();

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return locationId;
    }

    public String getLocationName(int locationId)
    {
        String locationName = "";

        Cursor cursor = database.query(SQLiteHelperClass.TBL_LOCATION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            Location location = cursorToLocation(cursor);

            if (locationId == location.getId())
                locationName = location.getName();

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return locationName;
    }

    public Location getLocationObject(int locationId)
    {
        Location location = null;

        String locationName = "";

        Cursor cursor = database.query(SQLiteHelperClass.TBL_LOCATION,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            location = cursorToLocation(cursor);

            if (locationId == location.getId())
                return location;

            cursor.moveToNext();
        }

        // make sure to close the cursor
        cursor.close();

        return location;
    }

    private Location cursorToLocation(Cursor cursor) {
        Location location = new Location();

        location.setId(cursor.getInt(0));
        location.setName(cursor.getString(1));
        location.setDescription(cursor.getString(2));

        return location;
    }


}