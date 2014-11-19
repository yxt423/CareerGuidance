package com.careerguidance.model;

import java.util.Date;
import java.util.*;

/**
 * Created by yxt on 11/16/14.
 */

public class User {
    int id;
    String lastName;
    String firstName;
    Date birthDate;
    String gender;

    String photoFileName;
    String username;
    String password;

    Hashtable<String, String> interests;

    Hashtable<String, Double> grades;

    Career careerChoice;

    University universityChoice;

    //Constructors
    public User()
    {
        id = 0;

        lastName = "";

        firstName = "";

        birthDate = new Date();

        gender = "";

        interests = new Hashtable<String, String>();

        grades = new Hashtable<String, Double>();

        careerChoice = null;

        universityChoice = null;
    }

    User(String lName, String fName, Date dob, String gndr)
    {
        lastName = lName;

        firstName = fName;

        birthDate = dob;

        gender = gndr;
    }

    //Getters
    public String getId()
    {
        return _id;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getFirstName() {

        return firstName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Career getCareerChoice() {
        return careerChoice;
    }

    public University getUniversityChoice() {
        return universityChoice;
    }

    //Setters
    public void setLastName(String lName) {
        lastName = lName;
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public void setBirthDate(Date bDate) {
        birthDate = Date;
    }

    public void setGender(String gndr) {
       gender = gndr;
    }

    public void setPhotoFileName(String fileName) {
        photoFileName = fileName;
    }

    public void setUsername(String uname) {
       username = uname;
    }

    public void setPassword(String pword) {
        password = pword;
    }

    public void setCareerChoice(Career career) {
        careerChoice = career;
    }

    public void setUniversityChoice(University uni) {
        universityChoice = uni;
    }

    public String [] getInterests()
    {
        return interests;
    }

    public String [] getGrades()
    {
        return grades;
    }
}
