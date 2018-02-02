package com.control;

public class Students  {
    public static final String DEFAULT_SURNAME = "Unknown";
    public static final String DEFAULT_SPECIALITY = "Mathematician";
    public static final String DEFAULT_UNIVERSITY = "BSU";

    String surname;
    String speciality;
    String university;

    public Students(String surname, String speciality, String university) {
        this.surname = surname;
        this.speciality = speciality;
        this.university = university;
    }


    public String getSurname() {
        return surname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getUniversity() {
        return university;
    }
}
