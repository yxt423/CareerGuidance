package com.careerguidance.model;

import java.util.ArrayList;

import java.util.Hashtable;

/**
 * Career class, define university instance and attributes of a career,
 * and APIs for accessing career data.
 */
public class Career extends BaseObject {

    double avgSalary;

    ArrayList<String> skillsRequired;

    Hashtable<String, Double> gradesRequired;

    ArrayList videoList;

    public Career()
    {
        super();
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
