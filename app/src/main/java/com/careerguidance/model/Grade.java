package com.careerguidance.model;

/**
 * Created by yxt on 11/16/14.
 */
public class Grade
{

    int id;

    Subject subject;

    double GPA;

    public Grade()
    {
        subject = null;

        GPA = 0.0;
    }

    public Grade(Subject subj, double theGPA)
    {

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
    public void setId(int subjectId) {
        id = subjectId;
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
