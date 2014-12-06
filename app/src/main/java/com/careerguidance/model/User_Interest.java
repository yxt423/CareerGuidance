package com.careerguidance.model;

/**
 * Created by yxt on 11/16/14.
 */
public class User_Interest
{

    int id;

    Interest interest;

    public User_Interest()
    {
        interest = null;
    }

    public User_Interest(Interest intrst)
    {
        interest = intrst;
    }

    //Getters
    public int getId()
    {
        return id;
    }

    public Interest getInterest()
    {
        return interest;
    }

    //Setters
    public void setInterest(Interest intrst)
    {
        interest = intrst;
    }

    public void setInterest(String intrst)
    {
        interest = new Interest(intrst);
    }

}
