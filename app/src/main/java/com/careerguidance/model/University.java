package com.careerguidance.model;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by yxt on 11/16/14.
 */
public class University {

    String name;

    String description;

    String location;

    double fees;

    URL url;

    ArrayList<Career> careerList;

    ArrayList<String> videoList;

    //Constructors
    public University() {
    }

    //Getters
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

    public ArrayList<Career> getCareerList() {
        return careerList;
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

    public void setCareerList(ArrayList<Career> lstCareer) {
        careerList = lstCareer;
    }

    public void setVideoList(ArrayList<String> vidList) {
        videoList = vidList;
    }
}
