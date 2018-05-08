package com.excel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.excel.DateFormulaParser.END;
import static com.excel.DateFormulaParser.MIN_START;

public class FormulaPatterns {
    public static final String INTEGER_NUMBER_PATTERN = "[1-9][0-9]*";
    public static final String DATE_PATTERN = "(((0[1-9]|[12][0-9])\\.(0[1-9]|1[0-2]))|(3[01]\\.(0[13578]|1[02]))|(30\\.(0[469]|11)))\\.([0-9][0-9][0-9][0-9])";
    public static final String CELL_PATTERN = "[A-Z]" + INTEGER_NUMBER_PATTERN;


    public static final String FIRST_DATE_FORMULA = "=" + DATE_PATTERN + "[+-]" + INTEGER_NUMBER_PATTERN;
    public static final String SECOND_DATE_FORMULA = "=" + CELL_PATTERN + "[+-]" + INTEGER_NUMBER_PATTERN;
    public static final String THIRD_DATE_FORMULA = "=" + CELL_PATTERN;
    public static final String FOURTH_DATE_FORMULA = "=" + DATE_PATTERN;


    public boolean isPattern(String string,String patternToFollow) {
        Pattern pattern = Pattern.compile(patternToFollow);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
    public boolean isMinMaxPattern(String formula,String start) {
        boolean isMinMax = false;
         if(formula.startsWith(start)){
            if(formula.endsWith(")")) {
                String temp = new String(formula);
                temp = temp.replaceAll(FormulaPatterns.DATE_PATTERN + ",","");
                temp = temp.replaceAll(FormulaPatterns.CELL_PATTERN + ",","");
                String patternData = findFirstPattern(temp,FormulaPatterns.DATE_PATTERN);
                String patternCell = findFirstPattern(temp,FormulaPatterns.CELL_PATTERN);
                if(patternData != null && patternCell == null) {
                    if(temp.endsWith(patternData + END)) {
                        temp = temp.replace(patternData,"");
                        if(temp.equals(start + END)) {
                            isMinMax = true;
                        }
                    }

                }
                else if(patternData == null && patternCell != null) {
                    if(temp.endsWith(patternCell + END)) {
                        temp = temp.replace(patternCell,"");
                        if(temp.equals(start + END)) {
                            isMinMax = true;
                        }
                    }
                }
            }
        }
        return isMinMax;
    }

    public ArrayList<String> findAllPatterns(String text, String patternToFollow) {
        ArrayList<String> output = new ArrayList<>();
        Pattern pattern = Pattern.compile(patternToFollow);
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            output.add(matcher.group());
        }
        return output;
    }
    public String findFirstPattern(String text, String patternToFollow) {
        Pattern pattern = Pattern.compile(patternToFollow);
        Matcher matcher = pattern.matcher(text);
        String temp = null;
        while(matcher.find()) {
            temp = matcher.group();
        }
        return temp;

    }




}
