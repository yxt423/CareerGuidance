package com.careerguidance.model;

import java.util.ArrayList;

import java.util.Hashtable;

/**
 * Created by yxt on 11/16/14.
 */
public class Career {
    int id;

    String name;

    String description;

    double avgSalary;

    ArrayList<String> skillsRequired;

    Hashtable<String, Double> gradesRequired;

    ArrayList videoList;

    public Career()
    {
        name = "";
        description = "";
        avgSalary = 0.00;
        skillsRequired = new ArrayList<String>();
        gradesRequired = new Hashtable<String, Double>();
        videoList = new ArrayList();
    }

    public Career(String careerName, String desc, double avgSal, ArrayList<String> reqSkills, Hashtable<String, Double> reqGrades, ArrayList vidList) {
        name = careerName;
        description = desc;
        avgSalary = avgSal;
        skillsRequired = reqSkills;
        gradesRequired = reqGrades;
        videoList = vidList;
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

    public void setName(String name) {
        this.name = name;
    }

    public double getAvgSalary() {
        return avgSalary;
    }

    public ArrayList<String> getSkillsRequired() {
        return skillsRequired;
    }

    public Hashtable<String, Double> getGradesRequired() {
        return gradesRequired;
    }

    public ArrayList getVideoList() {
        return videoList;
    }

    //Setters
    public void setDescription(String desc) {
        description = desc;
    }

    public void setAvgSalary(double avgSal) {
        avgSalary = avgSal;
    }

    public void setSkillsRequired(ArrayList<String> reqSkills) {
        skillsRequired = reqSkills;
    }

    public void setGradesRequired(Hashtable<String, Double> reqGrades) {
        gradesRequired = reqGrades;
    }

    public void setVideoList(ArrayList vidList) {
        videoList = vidList;
    }
}
