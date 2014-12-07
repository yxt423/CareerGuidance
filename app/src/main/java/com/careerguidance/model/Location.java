package com.careerguidance.model;

public class Location {
    int id;

    String name;

    String description;

    public Location()
    {
        name = "";
    }

    public Location(String locationName) {
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

    public void setName(String locationName) {
        name = locationName;
    }

    public void setDescription(String desc) {
        description = desc;
    }

}
