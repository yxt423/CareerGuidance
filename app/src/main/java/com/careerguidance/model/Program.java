package com.careerguidance.model;

/**
 * Created by yxt on 11/16/14.
 */
public class Program {

    int id;

    Subject subject;

    double GPA;

    public Program()
    {
        subject = null;

        GPA = 0.0;
    }

    public Program(Subject subj, double theGPA) {

        subject = subj;

        GPA = theGPA;
    }

    //Getters
    public int getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public double getGPA() {
        return GPA;
    }

    //Setters
    public void setId(int programId) {
        id = programId;
    }

    public void setSubject(Subject subj) {
        subject = subj;
    }

    public void setSubject(String subj) {
        subject = new Subject(subj);
    }

    public void setGPA(double theGPA) {
        GPA = theGPA;
    }

}
