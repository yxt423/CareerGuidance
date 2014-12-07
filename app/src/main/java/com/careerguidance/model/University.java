package com.careerguidance.model;

import java.net.URL;
import java.util.ArrayList;

/**
 * University class, define university instance and attributes of universities,
 * and APIs for accessing university data.
 */
public class University extends  Target {

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

    public ArrayList<Program> getUniversityList() {
        return programList;
    }

    public ArrayList<String> getVideoList() {
        return videoList;
    }

    //Setters
    public void setId(int uniId) {
        id = uniId;
    }

    public void setName(String strName) {
        name = strName;
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

    public void setProgramList(ArrayList<Program> lstProgram) {
        programList = lstProgram;
    }

    public void setVideoList(ArrayList<String> vidList) {
        videoList = vidList;
    }
}
