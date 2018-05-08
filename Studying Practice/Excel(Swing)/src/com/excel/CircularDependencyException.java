package com.excel;

/**
 * Created by LENOVO on 03.04.2018.
 */
public class CircularDependencyException extends Exception {
    public static final String CIRCULAR_DEPENDENCY = "Error! Circular Dependency Found";
    public CircularDependencyException(String s) {
        super(s);
    }
}
