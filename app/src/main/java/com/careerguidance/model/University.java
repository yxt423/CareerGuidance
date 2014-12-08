package com.careerguidance.model;

import java.net.URL;
import java.util.ArrayList;

/**
 * University class, define university instance and attributes of universities,
 * and APIs for accessing university data.
 */
public class University extends BaseObject {

    String location;

    double fees;

    URL url;

    ArrayList<Program> programList;

    ArrayList<String> videoList;

    //Constructors
    public University() {
        super();
    }

    //Getters
    public String getLocation() {
        return location;
    }

    public double getFees() {
        return fees;
    }

    public URL getUrl() {
        return url;
    }

    public ArrayList<Program> getUniversityList() {
        return programList;
    }

    public ArrayList<String> getVideoList() {
        return videoList;
    }

    //Setters

    public void setLocation(String locn) {
        location = locn;
    }

    public void setFees(double fee) {
        fees = fee;
    }

    public void setUrl(URL theUrl) {
        url = theUrl;
    }

    public void setProgramList(ArrayList<Program> lstProgram) {
        programList = lstProgram;
    }

    public void setVideoList(ArrayList<String> vidList) {
        videoList = vidList;
    }
}
