package com.control;

public class Student extends Pupil {
    private int course;

    public Student(String surname, String university, int mark, int course) {
        super(surname, university, mark);
        this.course = course;
    }
    public String toString() {
        super.toString();
        return "|Course:" + course;
    }
    public boolean equals(Student student) {
        boolean check = super.equals(student);
        if (check && this.course == student.course) {
            return true;
        } else return false;
    }
}
