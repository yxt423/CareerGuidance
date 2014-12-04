package com.careerguidance.model;

/**
 * Created by yxt on 11/16/14.
 */
public class Interest {
    int id;

    String name;
    String description;

    public Interest()
    {
        name = "";
    }

    public Interest(String interestName) {
        name = interestName;
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
    public void setName(String interestName) {
        name = interestName;
    }

    public void setDescription(String desc) {
        description = desc;
    }

}
