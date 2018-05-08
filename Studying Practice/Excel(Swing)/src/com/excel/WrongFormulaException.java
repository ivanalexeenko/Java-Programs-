package com.excel;

public class WrongFormulaException extends Exception {
    public static final String WRONG_FORMULA = "Error! Wrong Formula Input.";
    public WrongFormulaException(String s) {
        super(s);
    }
}
