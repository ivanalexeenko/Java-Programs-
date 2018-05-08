package com.excel;

public class EmptyCellException extends Exception {
    public static final String EMPTY_CELL = "Error! Referencing Cell is Empty";
    public EmptyCellException(String s) {
        super(s);
    }
}
