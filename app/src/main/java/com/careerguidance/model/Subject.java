package com.careerguidance.model;

/**
 * Created by yxt on 11/16/14.
 */
public class Subject {
    int id;

    String name;
    String description;

    public Subject()
    {
        name = "";
    }

    public Subject(String subjectName) {
        name = subjectName;
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
    public void setId(int subjectId) {
        id = subjectId;
    }

    public void setName(String subjectName) {
        name = subjectName;
    }

    public void setDescription(String desc) {
        description = desc;
    }

}
