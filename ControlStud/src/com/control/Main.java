package com.control;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String,Students> map = new HashMap<String, Students>();
        Container container = new Container(map);
        try {
            container.read("students.txt");
        } catch (IOException e) {
            System.out.println("Error input,data will be replaced by default");
            container.put(Students.DEFAULT_SURNAME,Students.DEFAULT_SPECIALITY,Students.DEFAULT_UNIVERSITY);
        } catch (MyException e) {
            System.out.println("Error input,data will be replaced by default");
            container.put(Students.DEFAULT_SURNAME,Students.DEFAULT_SPECIALITY,Students.DEFAULT_UNIVERSITY);
        }
        catch (NoSuchElementException e) {
            System.out.println("Error input,data will be replaced by default");
            container.put(Students.DEFAULT_SURNAME,Students.DEFAULT_SPECIALITY,Students.DEFAULT_UNIVERSITY);
        }
        ArrayList arrayList = new ArrayList();
        arrayList = container.getAllUniversities();
        System.out.println("All the Universities:");
        for(int i = 0;i < arrayList.size();i++) {
            System.out.println(arrayList.get(i));
        }
        System.out.println("Amount of Economists = " + container.getAllSpecialitiesAmount("Economist"));
        System.out.println("Amount of System_Programmers = " + container.getAllSpecialitiesAmount("System_Programmer"));
        System.out.println("Amount of Businessmen = " + container.getAllSpecialitiesAmount("Businessman"));




    }
}
