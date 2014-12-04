package com.careerguidance.model;

import android.content.Context;

import com.careerguidance.DBLayout.CareerDataSource;
import com.careerguidance.DBLayout.GenderDataSource;
import com.careerguidance.DBLayout.GradeDataSource;
import com.careerguidance.DBLayout.LocationDataSource;
import com.careerguidance.DBLayout.SubjectDataSource;

import java.util.Date;
import java.util.*;

/**
 * User class, define the user instance, it is used to show the information on
 * user profile page.
 */
public class User {

    private LocationDataSource locationDataSource;
    private GenderDataSource genderDataSource;
    private SubjectDataSource subjectDataSource;
    private GradeDataSource gradeDataSource;
    private CareerDataSource careerDataSource;


    private int id;
    private String lastName;
    private String firstName;
    private Date birthDate;
    private Gender gender;
    private Location location;

    private String photoFileName;
    private String username;
    private String password;

    private ArrayList<Interest> interests;

    private ArrayList<User_Grade> grades;

    private Career careerChoice;

    private University universityChoice;

    //Constructors
    public User(Context context)
    {
        id = 0;

        lastName = "";

        firstName = "";

        birthDate = new Date();

        gender = null;

        interests = new ArrayList<Interest>();

        grades = new ArrayList<User_Grade>();

        careerChoice = null;

        universityChoice = null;
    }

    public User()
    {
        id = 0;

        lastName = "";

        firstName = "";

        birthDate = new Date();

        gender = null;

        interests = new ArrayList<Interest>();

        grades = new ArrayList<User_Grade>();

        careerChoice = null;

        universityChoice = null;
    }

    User(Context context, String lName, String fName, Date dob, Gender gndr)
    {
        lastName = lName;

        firstName = fName;

        birthDate = dob;

        gender = gndr;
    }

    //Getters
    public int getId()
    {
        return id;
    }

    public String getFirstName() { return firstName; }

    public String getLastName()
    {
        return lastName;
    }

    public Location getLocation()
    {
        return location;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
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
    public void setId(int intId) {
        id = intId;
    }

    public void setLastName(String lName) {
        lastName = lName;
    }

    public void setLocation(Location locn) {
        location = locn;
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public void setBirthDate(Date bDate) { birthDate = bDate; }

    public void setGender(String gndr) {
       gender = new Gender(gndr);
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

    public ArrayList<Interest> getInterests()
    {
        return interests;
    }

    public ArrayList getGrades()
    {
        return grades;
    }

    //methods
    public boolean hasProfile ()
    {
        if (firstName.equals("") && lastName.equals(""))
            return false;

        return true;
    }




}
