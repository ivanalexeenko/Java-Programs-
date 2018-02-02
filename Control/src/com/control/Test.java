package com.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Test <Type extends Pupil> {

    private ArrayList<Type> arrayList;

    public Test(ArrayList<Type> arrayList) {
        this.arrayList = arrayList;
    }
    public void copy(ArrayList<Type> toCopy)  {
        toCopy = arrayList;
    }

    public int binarySearch(Pupil pupil) {
       return Collections.binarySearch(arrayList, pupil, new Comparator<Pupil>() {
            @Override
            public int compare(Pupil o1, Pupil o2) {
                if(o1.university == o2.university) {
                    return (o1.surname.compareTo(o2.surname));
                }
                else return (o1.surname.compareTo(o2.surname));
            }
        });

    }

}
