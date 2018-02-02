package com.control;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class Main {


    public static void main(String[] args) {
        Container<Car> cars = new Container<Car>();

        try {
            cars.read("input1.txt");
        } catch (IOException e) {
            System.out.println("File not found:" + e.getMessage() + ". Data will be replaced by default");
            cars.add(new Car(Car.DEFAULT_NAME,Car.DEFAULT_COLOR,Car.DEFAULT_FUEL,Car.DEFAULT_MATERIAL));
        } catch (MyException e) {
            System.out.println(e.getMessage());
            cars.add(new Car(Car.DEFAULT_NAME,Car.DEFAULT_COLOR,Car.DEFAULT_FUEL,Car.DEFAULT_MATERIAL));
        }
        catch (NoSuchElementException e) {
            System.out.println("Not enough elements, data will be replaced by default");
            cars.add(new Car(Car.DEFAULT_NAME,Car.DEFAULT_COLOR,Car.DEFAULT_FUEL,Car.DEFAULT_MATERIAL));
        }
        System.out.println("CARS:");
        System.out.println(cars.toString());

        Container <Bus> buses = new Container<Bus>();
        try {
            buses.read("input2.txt");
        } catch (IOException e) {
            System.out.println("File not found:" + e.getMessage() + ". Data will be replaced by default");
            buses.add(new Bus(Bus.DEFAULT_NAME,Bus.DEFAULT_COLOR,Bus.DEFAULT_FUEL,Bus.DEFAULT_PLACES,Bus.DEFAULT_DOORS));
        } catch (MyException e) {
            System.out.println(e.getMessage());
            buses.add(new Bus(Bus.DEFAULT_NAME,Bus.DEFAULT_COLOR,Bus.DEFAULT_FUEL,Bus.DEFAULT_PLACES,Bus.DEFAULT_DOORS));
        }
        catch (NoSuchElementException e) {
            System.out.println("Not enough elements, data will be replaced by default");
            buses.add(new Bus(Bus.DEFAULT_NAME,Bus.DEFAULT_COLOR,Bus.DEFAULT_FUEL,Bus.DEFAULT_PLACES,Bus.DEFAULT_DOORS));
        }
        System.out.println("BUSES:");
        System.out.println(buses.toString());

        Car toFind = new Car("Lamborgini", "Red" ,Vehicle.Fuel.DIESEL , Car.SeatMaterial.LEATHER);
        System.out.println("Amount of Elements<<" + toFind.toString() + ">> = " + cars.countVehicles(toFind));
        System.out.println("Index of Element<<" + toFind.toString() + ">> = " + cars.binarySearch(toFind));
        System.out.println("Maximum from Cars = <<" + cars.max() + ">>");
        System.out.println();

        Bus find = new Bus("Belaz","Yellow", Vehicle.Fuel.ELECTRO,100,3);
        System.out.println("Amount of Elements<<" + find.toString() + ">> = " + buses.countVehicles(find));
        System.out.println("Index of Element<<" + find.toString() + ">> = " + buses.binarySearch(find));
        System.out.println("Maximum from Buses = <<" + buses.max() + ">>");
        System.out.println();



    }
}
