package com.careerguidance.model;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by yxt on 11/16/14.
 */
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
    public void setName(String locationName) {
        name = locationName;
    }

    public void setDescription(String desc) {
        description = desc;
    }

}
