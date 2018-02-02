package com.control;

import java.io.File;

public class Car extends Vehicle {

    public static String DEFAULT_NAME = "Zhiguli";
    public static String DEFAULT_COLOR = "Purple";
    public static Vehicle.Fuel DEFAULT_FUEL = Vehicle.Fuel.PATROL;
    public static Car.SeatMaterial DEFAULT_MATERIAL = Car.SeatMaterial.LEATHER;

    enum SeatMaterial {LEATHER,TEXTILE,KASHMIR};
    private SeatMaterial seatMaterial;

    public Car(String name, String color, Fuel fuel,SeatMaterial seatMaterial) {
        this.name = name;
        this.color = color;
        this.fuel = fuel;
        this.seatMaterial = seatMaterial;
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
        buffer.append("|Seat Material:");
        buffer.append(seatMaterial);
        return buffer.toString();
    }
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}

