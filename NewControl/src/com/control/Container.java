package com.control;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Container<T extends Vehicle> extends ArrayList<T> {
    public String toString() {
        for(int i = 0;i < this.size();i++) {
            System.out.println(this.get(i).toString());
        }
        return "";
    }
    public int countVehicles(T vehicle) {
        return Collections.frequency(this,vehicle);
    }
    public int binarySearch(T vehicle) {
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if(!o1.name.equals(o2.name)) {
                    return o1.name.compareTo(o2.name);
                }
                else {
                    return o2.fuel.compareTo(o1.fuel);
                }
            };
        };
        return Collections.binarySearch(this,vehicle,comparator);
    }
    public void read(String filename) throws IOException, NoSuchElementException, MyException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        if(filename.equals("input1.txt")) {
            String n;
            String c;
            Vehicle.Fuel f;
            Car.SeatMaterial s;

            while(scanner.hasNext()) {
                n = scanner.next();
                c = scanner.next();
                String tmp = scanner.next();
                if(tmp.equals("PATROL")) {
                    f = Vehicle.Fuel.PATROL;
                }
                else if(tmp.equals("DIESEL")) {
                    f = Vehicle.Fuel.DIESEL;
                }
                else if (tmp.equals("ELECTRO")) {
                    f = Vehicle.Fuel.ELECTRO;
                }
                else {
                    throw new MyException("Wrong fuel input! Data will be replaced by default");
                }
                tmp = scanner.next();
                if(tmp.equals("LEATHER")) {
                    s = Car.SeatMaterial.LEATHER;
                }
                else if(tmp.equals("TEXTILE")) {
                    s = Car.SeatMaterial.TEXTILE;
                }
                else if(tmp.equals("KASHMIR")) {
                    s = Car.SeatMaterial.KASHMIR;
                }
                else {
                    throw new MyException("Wrong Seat Material input! Data will be replaced by default");
                }
                Vehicle vehicle = new Car(n,c,f,s);

                this.add((T)vehicle);
            }
        }
        else if(filename.equals("input2.txt")) {
            String n = scanner.next();
            String c = scanner.next();
            Vehicle.Fuel f;
            int p;
            int d;

            String tmp = scanner.next();
            if(tmp.equals("PATROL")) {
                f = Vehicle.Fuel.PATROL;
            }
            else if(tmp.equals("DIESEL")) {
                f = Vehicle.Fuel.DIESEL;
            }
            else if (tmp.equals("ELECTRO")) {
                f = Vehicle.Fuel.ELECTRO;
            }
            else {
                throw new MyException("Wrong fuel input! Data will be replaced by default");
            }
            p = scanner.nextInt();
            d = scanner.nextInt();
            Bus bus = new Bus(n,c,f,p,d);
            this.add((T)bus);
        }
        else throw new MyException("File not found!");

    }
    public T max() {
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                if(!o1.name.equals(o2.name)) {
                    return o1.name.compareTo(o2.name);
                }
                else {
                    return o2.fuel.compareTo(o1.fuel);
                }
            };
        };
        return Collections.max(this,comparator);
    }


}
