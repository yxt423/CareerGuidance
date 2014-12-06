package com.careerguidance.model;

import android.content.Context;
import android.util.Log;

import com.careerguidance.cgexception.CGException;
import com.careerguidance.dblayout.CareerDataSource;
import com.careerguidance.dblayout.GenderDataSource;
import com.careerguidance.dblayout.User_GradeDataSource;
import com.careerguidance.dblayout.LocationDataSource;
import com.careerguidance.dblayout.SubjectDataSource;

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
    private User_GradeDataSource userGradeDataSource;
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

    private ArrayList<Grade> grades;

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

        grades = new ArrayList<Grade>();

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

        grades = new ArrayList<Grade>();

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
        ArrayList<Interest> tempInterests = (ArrayList<Interest>) interests.clone();

        return tempInterests;
    }

    public ArrayList<Grade> getGrades()
    {
        ArrayList<Grade> tempGrades = (ArrayList<Grade>) grades.clone();

        return tempGrades;
    }

    //methods
    public boolean hasProfile ()
    {
        if (firstName != null & lastName != null)
            return true;

        return false;
    }


    public void addGrade (Grade grade)
    {
        try
        {
            if (!grades.contains(grade)) {
                grades.add(grade);
            }
            else
            {
                CGException exception = new CGException("Grade already exists");

                throw exception;
            }
        }
        catch (CGException e)
        {
            Log.d("CGException:", e.toString());
        }
    }

    public void delGrade (Grade grade)
    {
        try
        {
            if (grades.contains(grade))
            {
                grades.remove(grade);
            }
            else
            {
                CGException exception = new CGException("Grade does not exist");

                throw exception;
            }
        }
        catch (CGException exception)
        {
            Log.d("CGException:", exception.toString());
        }
    }

    public void addInterest (Interest interest)
    {
        try
        {
            if (!interests.contains(interest))
            {
                interests.add(interest);
            }
            else
            {
                CGException exception = new CGException("Interest already exists");

                throw exception;
            }
        }
        catch (CGException e)
        {
            Log.d("CGException:", e.toString());
        }
    }

    public void delInterest (Interest interest)
    {
        try
        {
            if (interests.contains(interest))
            {
                grades.remove(interest);
            }
            else
            {
                CGException exception = new CGException("Interest does not exist");

                throw exception;
            }
        }
        catch (CGException exception)
        {
            Log.d("CGException:", exception.toString());
        }
    }
}
