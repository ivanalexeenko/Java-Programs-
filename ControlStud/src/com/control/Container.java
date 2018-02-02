package com.control;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Container {
    private HashMap<String, Students> map;

    public Container(HashMap<String, Students> map) {
        this.map = map;
    }

    public void read(String filename) throws IOException, NoSuchElementException, MyException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        String sur;
        String spec;
        String uni;
        while (scanner.hasNext()) {
            sur = scanner.next();
            spec = scanner.next();
            uni = scanner.next();
            map.put(sur, new Students(sur, spec, uni));
        }

    }

    public void put(String sur, String spec, String uni) {
        map.put(sur, new Students(sur, spec, uni));
    }

    public ArrayList<String> getAllUniversities() {

        Set<String> set = new HashSet<>();
        for(Map.Entry<String, Students> key : map.entrySet()) {
            set.add(key.getValue().getUniversity());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(set);
        return arrayList;
    }

    public int getAllSpecialitiesAmount( String uni) {

        int count = 0;
        for(Map.Entry<String, Students> key : map.entrySet()) {
            if(key.getValue().getSpeciality().equals(uni)) {
                count++;
            }
        }
        return count;
    }

}