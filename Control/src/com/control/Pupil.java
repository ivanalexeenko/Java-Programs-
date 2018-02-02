package com.control;

public abstract class Pupil {
    protected String surname;
    protected String university;
    protected int mark;

    public Pupil(String surname, String university, int mark) {
        this.surname = surname;
        this.university = university;
        this.mark = mark;
    }

    public String toString() {
        return "Surname:" + surname + "|University:" + university + "|Mark:" + mark;
    }
    public boolean equals(Pupil pupil) {
        if(surname.equals(pupil.surname) && university.equals(pupil.university) && mark == pupil.mark) {
            return true;
        }
        else return false;
    }
}