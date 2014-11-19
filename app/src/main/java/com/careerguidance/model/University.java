package com.careerguidance.model;

import java.net.URL;
import java.util.ArrayList;

/**
 * University class, define university instance and attributes of universities,
 * and APIs for accessing university data.
 */
public class University {

    int id;

    String name;

    String description;

    String location;

    double fees;

    URL url;

    ArrayList<University> universityList;

    ArrayList<String> videoList;

    //Constructors
    public University() {
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

    public String getLocation() {
        return location;
    }

    public double getFees() {
        return fees;
    }

    public URL getUrl() {
        return url;
    }

    public ArrayList<University> getUniversityList() {
        return universityList;
    }

    public ArrayList<String> getVideoList() {
        return videoList;
    }

    //Setters
    public void setName(String name) {
        name = name;
    }

    public void setDescription(String desc) {
        description = desc;
    }

    public void setLocation(String locn) {
        location = locn;
    }

    public void setFees(double fee) {
        fees = fee;
    }

    public void setUrl(URL theUrl) {
        url = theUrl;
    }

    public void setCareerList(ArrayList<University> lstUniversity) {
        universityList = lstUniversity;
    }

    public void setVideoList(ArrayList<String> vidList) {
        videoList = vidList;
    }
}
