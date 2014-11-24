package com.careerguidance.model;

/**
 * Created by yxt on 11/16/14.
 */
public class Gender {
    int id;

    String name;

    public Gender()
    {
        name = "";
    }

    public Gender(String gndr) {
        name = gndr;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    //Setters
    public void setName(String gndr) {
        name = gndr;
    }


}
