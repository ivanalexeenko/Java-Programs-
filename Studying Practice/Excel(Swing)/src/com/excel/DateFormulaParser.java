package com.excel;

import java.util.*;

public class DateFormulaParser {
    private FormulaPatterns formulaPatterns;
    int amountOfCells = DateTableModel.ROWS * DateTableModel.COLUMNS;
    public DateFormulaParser() {
        formulaPatterns = new FormulaPatterns();
        datesList = new ArrayList<>();
        datePatterns = new ArrayList<>();
        cellPatterns = new ArrayList<>();

    }
    public static final String MIN_START = "=min(";
    public static final String MAX_START = "=max(";
    public static final String END = ")";

    ArrayList<GregorianCalendar> datesList;
    ArrayList<String> datePatterns;
    ArrayList<String> cellPatterns;
    public int matrixIndicesToLinear (int rowIndex,int colIndex,int rows,int columns) {
        return (rowIndex - 1) * columns + colIndex;
    }
    public GregorianCalendar findMinMax(String formula,GregorianCalendar[][] dates,String minOrMax) throws WrongFormulaException, EmptyCellException, CellRangeException {
        datesList.clear();
        datePatterns.clear();
        cellPatterns.clear();

        datePatterns = formulaPatterns.findAllPatterns(formula,FormulaPatterns.DATE_PATTERN);
        cellPatterns = formulaPatterns.findAllPatterns(formula,FormulaPatterns.CELL_PATTERN);
        Iterator iterator = datePatterns.iterator();
        while(iterator.hasNext()) {
            String s = (String) iterator.next();
            datesList.add(parseDate(s,dates));
        }
        iterator = cellPatterns.iterator();
        while(iterator.hasNext()) {
            String s = (String) iterator.next();
            datesList.add(parseCell(s,dates));
        }
        Collections.sort(datesList, new Comparator<GregorianCalendar>() {
            @Override
            public int compare(GregorianCalendar o1, GregorianCalendar o2) {
                if(o1.get(Calendar.YEAR) == o2.get(Calendar.YEAR)) {
                    if(o1.get(Calendar.MONTH) == o2.get(Calendar.MONTH)) {
                        return o1.get(Calendar.DAY_OF_MONTH) - o2.get(Calendar.DAY_OF_MONTH);
                    }
                    return o1.get(Calendar.MONTH) - o2.get(Calendar.MONTH);
                }
                return o1.get(Calendar.YEAR) - o2.get(Calendar.YEAR);
            }
        });
        if(minOrMax.equals(MIN_START)) {
            return datesList.get(0);
        }
        else if(minOrMax.equals(MAX_START)) {
            return datesList.get(datesList.size() - 1);
        }
        else return null;

    }
    public GregorianCalendar parseDate(String formula,GregorianCalendar[][] dates) throws WrongFormulaException {
        boolean isPattern = formulaPatterns.isPattern(formula,FormulaPatterns.DATE_PATTERN);
        if(!isPattern) {
            throw new WrongFormulaException(WrongFormulaException.WRONG_FORMULA);
        }
        int day = Integer.parseInt(formula.substring(0,2));
        int month = Integer.parseInt(formula.substring(3,5));
        int year = Integer.parseInt(formula.substring(6,10));
        return  new GregorianCalendar(year,month - 1,day);
    }
    public GregorianCalendar parseCell(String formula,GregorianCalendar[][] dates) throws WrongFormulaException, CellRangeException, EmptyCellException {
        boolean isPattern = formulaPatterns.isPattern(formula,FormulaPatterns.CELL_PATTERN);
        if(!isPattern) {
            throw new WrongFormulaException(WrongFormulaException.WRONG_FORMULA);
        }
        int columnIndex = DateTableModel.letters.indexOf(formula.charAt(0)) + 1;
        int rowIndex = Integer.parseInt(formula.substring(1,formula.length()));
        if(columnIndex >= DateTableModel.COLUMNS || rowIndex >= DateTableModel.ROWS) {
            throw new CellRangeException(CellRangeException.OUT_OF_RANGE);
        }
        if(dates[rowIndex][columnIndex] == null) {
            throw new EmptyCellException(EmptyCellException.EMPTY_CELL);
        }
        GregorianCalendar temp1 = dates[rowIndex][columnIndex];
        int year = temp1.get(Calendar.YEAR);
        int month = temp1.get(Calendar.MONTH);
        int day = temp1.get(Calendar.DAY_OF_MONTH);
        return new GregorianCalendar(year,month,day);

    }
    public GregorianCalendar getDate(String formula,GregorianCalendar[][] dates) throws WrongFormulaException, EmptyCellException, CellRangeException, CircularDependencyException {

        boolean isFirst = formulaPatterns.isPattern(formula,FormulaPatterns.FIRST_DATE_FORMULA);
        boolean isSecond = formulaPatterns.isPattern(formula,FormulaPatterns.SECOND_DATE_FORMULA);
        boolean isThird = formulaPatterns.isPattern(formula,FormulaPatterns.THIRD_DATE_FORMULA);
        boolean isFourth = formulaPatterns.isPattern(formula,FormulaPatterns.FOURTH_DATE_FORMULA);

        if(isFirst || isFourth) {
            try {
                String dateOneText = formulaPatterns.findFirstPattern(formula,FormulaPatterns.DATE_PATTERN);
                if(dateOneText == null) {
                    throw new WrongFormulaException(WrongFormulaException.WRONG_FORMULA);
                }
                StringBuffer buffer = new StringBuffer(formula);
                int day = Integer.parseInt(dateOneText.substring(0,2));
                int month = Integer.parseInt(dateOneText.substring(3,5));
                int year = Integer.parseInt(dateOneText.substring(6,10));
                GregorianCalendar date = new GregorianCalendar(year,month - 1,day);
                if(isFourth) {
                    return date;
                }
                int start = formula.indexOf(dateOneText);
                buffer.delete(0,start + dateOneText.length());
                int addition = Integer.parseInt(buffer.substring(1,buffer.length()));
                if(buffer.charAt(0) == '-') {
                    addition = - addition;
                }
                date.add(Calendar.DATE,addition);
                return date;
            }
            catch (WrongFormulaException e) {
                throw new WrongFormulaException(WrongFormulaException.WRONG_FORMULA);
            }

        }
        else if(isSecond || isThird) {
            try{
                String cellText = formulaPatterns.findFirstPattern(formula,FormulaPatterns.CELL_PATTERN);
                if(cellText == null) {
                    throw new WrongFormulaException(WrongFormulaException.WRONG_FORMULA);
                }
                int columnIndex = DateTableModel.letters.indexOf(cellText.charAt(0)) + 1;
                int rowIndex = Integer.parseInt(cellText.substring(1,cellText.length()));
                if(columnIndex >= DateTableModel.COLUMNS || rowIndex >= DateTableModel.ROWS) {
                    throw new CellRangeException(CellRangeException.OUT_OF_RANGE);
                }
                if(dates[rowIndex][columnIndex] == null) {
                    throw new EmptyCellException(EmptyCellException.EMPTY_CELL);
                }
                int indexFrom = matrixIndicesToLinear(Application.selectedRow,Application.selectedColumn,DateTableModel.ROWS,DateTableModel.COLUMNS);
                int indexTo = matrixIndicesToLinear(rowIndex,columnIndex,DateTableModel.ROWS,DateTableModel.COLUMNS);


                if(isThird) {
                    GregorianCalendar temp1 = dates[rowIndex][columnIndex];
                    int year = temp1.get(Calendar.YEAR);
                    int month = temp1.get(Calendar.MONTH);
                    int day = temp1.get(Calendar.DAY_OF_MONTH);
                    return new GregorianCalendar(year,month,day);
                }
                GregorianCalendar date = dates[rowIndex][columnIndex];
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH);
                int day = date.get(Calendar.DAY_OF_MONTH);
                GregorianCalendar temp2 = new GregorianCalendar(year,month,day);

                StringBuffer buffer = new StringBuffer(formula);
                int start = formula.indexOf(cellText);
                buffer.delete(0,start + cellText.length());
                int addition = Integer.parseInt(buffer.substring(1,buffer.length()));
                if(buffer.charAt(0) == '-') {
                    addition = - addition;
                }
                temp2.add(Calendar.DATE,addition);
                return temp2;
            }
            catch (WrongFormulaException e) {
                throw new WrongFormulaException(WrongFormulaException.WRONG_FORMULA);
            }

        }
        else if(formulaPatterns.isMinMaxPattern(formula,MIN_START)) {
            return findMinMax(formula,dates,MIN_START);
        }
        else if(formulaPatterns.isMinMaxPattern(formula,MAX_START)) {
            return findMinMax(formula,dates,MAX_START);
        }
        else throw new WrongFormulaException(WrongFormulaException.WRONG_FORMULA);

    }
}
