package com.careerguidance.model;

/**
 * Created by yxt on 12/7/14.
 */
public abstract class BaseObject {
    int id;

    String name;

    String description;

    public BaseObject() {
        name = "";
        description = "";
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

    // setter

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int careerId) {
        id = careerId;
    }

    public void setDescription(String desc) {
        description = desc;
    }
}
