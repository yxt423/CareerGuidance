package com.careerguidance.model;

import com.careerguidance.dblayout.GenderDataSource;

/**
 * Created by yxt on 11/16/14.
 */
public class Gender
{
    int id;

    String name;

    GenderDataSource genderDataSource;

    public Gender()
    {
        name = "";
    }

    public Gender(String gndr)
    {
        name = gndr;
    }

    public Gender(int genderId, String genderName)
    {
        id = genderId;

        name = genderName;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //Setters
    public void setId(int genderId) {
        id = genderId;
    }

    public void setName(String gndr) {
        name = gndr;
    }


}
