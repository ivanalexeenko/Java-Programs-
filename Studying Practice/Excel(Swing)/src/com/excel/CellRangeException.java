package com.excel;

/**
 * Created by LENOVO on 01.04.2018.
 */
public class CellRangeException extends Exception {
    public static final String OUT_OF_RANGE = "Error! Cell index is Out of Range";
    public CellRangeException(String s) {
        super(s);
    }
}
