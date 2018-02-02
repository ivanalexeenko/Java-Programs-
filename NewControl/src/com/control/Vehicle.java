package com.control;

public abstract class Vehicle {
    protected String name;
    protected String color;
    enum Fuel {PATROL,DIESEL,ELECTRO};
    protected Fuel fuel;

    public abstract String toString();

    @Override
    public boolean equals(Object obj) {
        Vehicle vehicle = (Vehicle)obj;
        if(name.equals(vehicle.name) && color.equals(vehicle.color) && fuel.equals(vehicle.fuel)) {
            return true;
        }
        else return false;
    }


    }


