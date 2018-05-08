package com.lalala;
public class Student {
    private Integer course;
    private Integer group;
    private String[] NFS;

    public Student(Integer course, Integer group, String[] NFS) {
        this.course = course;
        this.group = group;
        this.NFS = NFS;
    }

    public Student(Integer course, Integer group,String name,String fatherName,String surname) {
        this.course = course;
        this.group = group;
        String[] nfs = {name,fatherName,surname};
        this.NFS = nfs;
    }

    public Integer getCourse() {

        return course;
    }

    public Integer getGroup() {
        return group;
    }

    public String[] getNFS() {
        return NFS;
    }
}
