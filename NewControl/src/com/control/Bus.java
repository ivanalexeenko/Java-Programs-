package com.control;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Bus extends Vehicle {

    public static String DEFAULT_NAME = "MTZ";
    public static String DEFAULT_COLOR = "Green";
    public static Vehicle.Fuel DEFAULT_FUEL = Vehicle.Fuel.PATROL;
    public static int DEFAULT_PLACES = 1;
    public static int DEFAULT_DOORS = 3;

    private int places;
    private int doors;

    public Bus(String name, String color, Fuel fuel,int places,int doors ) {
        this.name = name;
        this.color = color;
        this.fuel = fuel;
        this.places = places;
        this.doors = doors;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer("");
        buffer.append("Name:");
        buffer.append(name);
        buffer.append("|Color:");
        buffer.append(color);
        buffer.append("|Fuel:");
        buffer.append(fuel);
        buffer.append("|Places:");
        buffer.append(places);
        buffer.append("|Doors:");
        buffer.append(doors);
        return buffer.toString();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
