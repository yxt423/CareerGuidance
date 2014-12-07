package com.careerguidance.model;

import com.careerguidance.dblayout.LocationDataSource;

public class Location {
    int id;

    String name;

    String description;

    public Location()
    {
        name = "";
    }

    public Location(String locationName)
    {
        name = locationName;

    }

    public Location(int locationId)
    {
        id = locationId;
    }

    public Location(int locationId, String locationName)
    {
        id = locationId;

        name = locationName;
    }
    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    //Setters
    public void setId(int locationId)
    {
        id = locationId;
    }

    public void setId(String location)
    {
        name = location;
    }
    public void setName(String locationName) {
        name = locationName;
    }

    public void setDescription(String desc) {
        description = desc;
    }

}
