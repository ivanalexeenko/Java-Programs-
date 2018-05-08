package com.excel;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public class DateTableModel extends DefaultTableModel {
    public static final int ROWS = 20;
    public static final int COLUMNS = 15;
    public static final String DATE_FORMAT = "dd.MM.yyyy";
    private Integer[] rowNames;
    private String[] columnNames;
    public static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String[][] formulas;
    private GregorianCalendar[][] dates;
    private SimpleDateFormat dateFormat;
    DateFormulaParser dateFormulaParser;

    public String[][] getFormulas() {
        return formulas;
    }

    public GregorianCalendar[][] getDates() {
        return dates;
    }

    public DateTableModel() {
        rowNames = new Integer[ROWS];
        columnNames = new String[COLUMNS];

        formulas = new String[ROWS][COLUMNS];
        dates = new GregorianCalendar[ROWS][COLUMNS];
        for(int i = 1;i < ROWS;i++) {
            rowNames[i] = i;

        }
        for(int i = 1;i < COLUMNS;i++) {
            columnNames[i] = letters.substring(i - 1,i);
        }
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormulaParser = new DateFormulaParser();


    }
    @Override
    public int getRowCount() {
        return ROWS;
    }

    @Override
    public int getColumnCount() {
        return COLUMNS;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex == 0 && columnIndex != 0) {
            return columnNames[columnIndex];
        }
        else if(rowIndex != 0 && columnIndex == 0) {
            return rowNames[rowIndex];
        }
        else if(rowIndex == 0 && columnIndex == 0) {
           return null;
        }
        else {
            if(formulas[rowIndex][columnIndex] == null || formulas[rowIndex][columnIndex].equals("")) {
                return "";
            }

            try {
                GregorianCalendar date = dateFormulaParser.getDate(formulas[rowIndex][columnIndex],dates);
                dates[rowIndex][columnIndex] = date;
                return dateFormat.format(dates[rowIndex][columnIndex].getTime());
            } catch (WrongFormulaException | EmptyCellException | CellRangeException | CircularDependencyException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
                //dates[rowIndex][columnIndex] = null;
                //formulas[rowIndex][columnIndex] = "";
                return "";
            }

        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if(rowIndex >= DateTableModel.ROWS || rowIndex < 0 || columnIndex >= DateTableModel.COLUMNS || columnIndex < 0) {
            try {
                throw new CellRangeException(CellRangeException.OUT_OF_RANGE);
            } catch (CellRangeException e) {
                JOptionPane.showMessageDialog(null,e.getMessage(),"An Error Occured",JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            if(aValue.equals("")) {
                formulas[rowIndex][columnIndex] = "";
            }
            else {
                formulas[rowIndex][columnIndex] = (String)aValue;
            }
        }
        fireTableDataChanged();



    }
}
