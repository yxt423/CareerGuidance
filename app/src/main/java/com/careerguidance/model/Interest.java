package com.careerguidance.model;

/**
 * Created by yxt on 11/16/14.
 */
public class Interest extends Target{

    public Interest()
    {
        super();
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
    public void setId(int interestId) {
        id = interestId;
    }

    public void setName(String interestName) {
        name = interestName;
    }

    public void setDescription(String desc) {
        description = desc;
    }

}
