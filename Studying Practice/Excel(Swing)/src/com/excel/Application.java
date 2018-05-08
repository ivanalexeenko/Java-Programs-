package com.excel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class Application extends JFrame {
    public static final String CAPTION = "EXCEL 2018 BRAND NEW PROG.";
    public static int selectedRow = 0;
    public static int selectedColumn = 0;
    private DateTableModel dateTableModel;
    private JTable dateTable;
    private JPanel dateTablePanel;
    private JTextField formulaField;
    DateFormulaParser dateFormulaParser;
    private String[][] formulas;
    private GregorianCalendar[][] dates;

    public Application() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(CAPTION);
        setResizable(false);
        dateFormulaParser = new DateFormulaParser();
        dateTableModel = new DateTableModel();
        formulas = dateTableModel.getFormulas();
        dates = dateTableModel.getDates();
        dateTable = new JTable(dateTableModel);
        ListSelectionModel listSelectionModel = dateTable.getSelectionModel();
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int indexOne = dateTable.getSelectedRow();
                int indexTwo = dateTable.getSelectedColumn();
                if(indexOne >= 1 && indexOne < DateTableModel.ROWS && indexTwo >=1 && indexTwo < DateTableModel.COLUMNS) {
                    selectedRow = indexOne;
                    selectedColumn = indexTwo;
                    if(formulas[indexOne][indexTwo] != null) {
                        formulaField.setText(formulas[indexOne][indexTwo]);
                    }
                    else formulaField.setText("");
                }

            }
        });
        formulaField = new JTextField();
        formulaField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GregorianCalendar date;
                try {
                    if(formulaField.getText().equals("")) {
                        dateTableModel.setValueAt("",selectedRow,selectedColumn);
                    }
                    else {
                        date = dateFormulaParser.getDate(formulaField.getText(),dates);
                        dateTableModel.setValueAt(formulaField.getText(),selectedRow,selectedColumn);
                    }

                } catch (WrongFormulaException | EmptyCellException | CellRangeException | CircularDependencyException e1) {
                    JOptionPane.showMessageDialog(null,e1.getMessage());
                    dateTableModel.setValueAt("",selectedRow,selectedColumn);
                    formulaField.setText(formulas[selectedRow][selectedColumn]);
                }

            }
        });
        dateTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        dateTable.setSelectionBackground(Color.white);

        setLayout(new BorderLayout());
        dateTablePanel = new JPanel();
        dateTablePanel.setLayout(new BorderLayout());
        dateTablePanel.add(dateTable,BorderLayout.SOUTH);
        dateTablePanel.add(formulaField,BorderLayout.NORTH);
        add(dateTablePanel,BorderLayout.NORTH);

    }

}
