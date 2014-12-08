package com.careerguidance.model;

public class Location extends BaseObject {

    public Location()
    {
        super();
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

    //Setters
    public void setId(String location)
    {
        name = location;
    }

}
