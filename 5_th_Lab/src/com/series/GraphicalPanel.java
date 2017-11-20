package com.series;

import javax.management.ObjectName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicalPanel extends JPanel {
    // Constants
    public static final double EXAMPLE_FIRST_ELEMENT = 3;
    public static final double EXAMPLE_COEFFICIENT = 3;

    public static final int EXAMPLE_INDEX = 10;
    public static final int FONT_SIZE = 17 ;
    public static final int DEFAULT_WIDTH = 650;
    public static final int DEFAULT_HEIGHT = 450;
    public static final int BUTTONS_WIDTH = 100;
    public static final int BUTTONS_HEIGHT = 50;
    public static final int BIG_BUTTON_WIDTH = 300;
    public static final int BIG_BUTTON_HEIGHT = 50;

    public static final String FONT_STYLE = "Bernard MT Condensed";
    public static final String SERIES_TYPE = "Choose a type of Series:";
    public static final String LINER_TYPE = "Liner";
    public static final String EXPONENTIAL_TYPE = "Exponential";
    public static final String BACKGROUND_IMAGE = "background.jpg";
    public static final String FIRST_ELEMENT_TEXT = "First element:";
    public static final String COEFFICIENT_TEXT = "Coefficient:";
    public static final String AMOUNT_TEXT = "Amount:";
    public static final String FILE_TO_SAVE_TEXT = "File to save:";
    public static final String SUBMIT_TEXT = "Submit";
    public static final String MESSAGE_BOX_TEXT = "Wrong input,data will be replaced by default.";
    public static final String MESSAGE_BOX_SOFT_TEXT = "Series type is not chosen, Liner is default.";
    public static final String EXAMPLE_FILENAME = "output.txt";
    // Buttons and others
    private JRadioButton linerRadioButton;
    private JRadioButton exponentialRadioButton;
    private JTextField firstElementTextField;
    private JTextField firstElementInput;
    private JTextField coefficientTextField;
    private JTextField coefficientInput;
    private JTextField amountTextField;
    private JTextField amountInput;
    private JTextField fileToSaveTextField;
    private JTextField fileToSaveInput;
    private JTextField showSeries;
    private JButton submitButton;


    private Series series;

    private Double firstElement;
    private Integer amount;
    private Double coefficient;
    private String filename;
    private String outputText;
    private int sum;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        GridBagLayout gridBagLayout = new GridBagLayout();

        setLayout(gridBagLayout);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.CENTER;


        Font font = new Font(FONT_STYLE,Font.ITALIC,FONT_SIZE);

        g.setFont(font);

        g.setColor(Color.white);



        Image background = new ImageIcon(Application.class.getResource(BACKGROUND_IMAGE)).getImage();

        g.drawImage(background,0,0,Application.DEFAULT_WIDTH,Application.DEFAULT_HEIGHT,null);

        int xCenter = (DEFAULT_WIDTH / 2) - (SERIES_TYPE.length() * FONT_SIZE / 2) / 2;

        g.drawString(SERIES_TYPE,xCenter,FONT_SIZE);

        constraints.ipadx = BUTTONS_WIDTH;
        constraints.ipady = BUTTONS_HEIGHT;


            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(linerRadioButton);
            buttonGroup.add(exponentialRadioButton);
            linerRadioButton = new JRadioButton(LINER_TYPE);
            linerRadioButton.setFont(font);
            linerRadioButton.setOpaque(false);
            linerRadioButton.setForeground(Color.white);
            constraints.weightx = 0.0;
            constraints.gridx = 1;
            constraints.gridy = 0;
            add(linerRadioButton,constraints);


            exponentialRadioButton = new JRadioButton(EXPONENTIAL_TYPE);
            exponentialRadioButton.setOpaque(false);
            exponentialRadioButton.setForeground(Color.white);
            exponentialRadioButton.setFont(font);
            constraints.gridx = 3;
            constraints.gridy = 0;
            add(exponentialRadioButton,constraints);

            constraints.ipadx = BUTTONS_WIDTH;


            firstElementTextField = new JTextField(FIRST_ELEMENT_TEXT);
            firstElementTextField.setEnabled(false);
            firstElementTextField.setOpaque(false);
            firstElementTextField.setFont(font);
            constraints.weightx = 0.0;
            constraints.gridx = 0;
            constraints.gridy = 1;
            add(firstElementTextField,constraints);

            firstElementInput = new JTextField();
            firstElementInput.setOpaque(true);
            firstElementInput.setFont(font);
            constraints.weightx = 0.0;
            constraints.gridx = 1;
            constraints.gridy = 1;
            add(firstElementInput,constraints);

            coefficientTextField = new JTextField(COEFFICIENT_TEXT);
            coefficientTextField.setEnabled(false);
            coefficientTextField.setOpaque(false);
            coefficientTextField.setFont(font);
            constraints.weightx = 0.0;
            constraints.gridx = 2;
            constraints.gridy = 1;
            add(coefficientTextField,constraints);

            coefficientInput = new JTextField();
            coefficientInput.setOpaque(true);
            coefficientInput.setFont(font);
            constraints.weightx = 0.0;
            constraints.gridx = 3;
            constraints.gridy = 1;
            add(coefficientInput,constraints);

            amountTextField = new JTextField(AMOUNT_TEXT);
            amountTextField.setEnabled(false);
            amountTextField.setOpaque(false);
            amountTextField.setFont(font);
            constraints.weightx = 0.0;
            constraints.gridx = 0;
            constraints.gridy = 2;
            add(amountTextField,constraints);

            amountInput = new JTextField();
            amountInput.setOpaque(true);
            amountInput.setFont(font);
            constraints.weightx = 0.0;
            constraints.gridx = 1;
            constraints.gridy = 2;
            add(amountInput,constraints);

            fileToSaveTextField = new JTextField(FILE_TO_SAVE_TEXT);
            fileToSaveTextField.setEnabled(false);
            fileToSaveTextField.setOpaque(false);
            fileToSaveTextField.setFont(font);
            constraints.weightx = 0.0;
            constraints.gridx = 2;
            constraints.gridy = 2;
            add(fileToSaveTextField,constraints);

            fileToSaveInput = new JTextField();
            fileToSaveInput.setOpaque(true);
            fileToSaveInput.setFont(font);
            constraints.weightx = 0.0;
            constraints.gridx = 3;
            constraints.gridy = 2;
            add(fileToSaveInput,constraints);

            constraints.ipadx = BIG_BUTTON_WIDTH;
            constraints.ipady = BIG_BUTTON_HEIGHT;


            showSeries = new JTextField();
            showSeries.setOpaque(true);
            showSeries.setFont(font);
            showSeries.setEditable(false);
            constraints.weightx = 0.0;
            constraints.gridx = 0;
            constraints.gridy = 3;
            constraints.gridwidth = 4;
            add(showSeries,constraints);

            submitButton = new JButton(SUBMIT_TEXT);
            submitButton.setFont(font);
            constraints.weightx = 0.0;
            constraints.gridx = 0;
            constraints.gridy = 4;
            constraints.gridwidth = 0;
            add(submitButton,constraints);


            ApplicationListener submitButtonAction = new ApplicationListener();

            submitButton.addActionListener(submitButtonAction);





    }

    public class ApplicationListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            firstElement = Double.valueOf(0);
            amount = Integer.valueOf(0);
            coefficient = Double.valueOf(0);
            filename = "";
            try {
                firstElementInput.setEditable(true);
                firstElementInput.setEnabled(true);

                firstElement = Double.parseDouble(firstElementInput.getText());
                System.out.println("????");
                amount = Integer.parseInt(amountInput.getText());
                coefficient = Double.parseDouble(coefficientInput.getText());
                filename = fileToSaveInput.getText();
                System.out.println(filename);
                if(linerRadioButton.isSelected()) {
                    series = new Liner(firstElement.intValue(),coefficient.intValue());
                    System.out.println("1st");
                }
                else if(exponentialRadioButton.isSelected()) {
                    series = new Exponential(firstElement.intValue(),coefficient.intValue());
                    System.out.println("2st");
                }
                else {
                    throw new MyException(MESSAGE_BOX_SOFT_TEXT);
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,MESSAGE_BOX_TEXT);
                firstElement = EXAMPLE_FIRST_ELEMENT;
                amount = EXAMPLE_INDEX;
                coefficient = EXAMPLE_COEFFICIENT;
                filename = EXAMPLE_FILENAME;
                series = new Liner(firstElement.intValue(),coefficient.intValue());
            }
            catch (MyException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
                series = new Liner(firstElement.intValue(),coefficient.intValue());
            }

            //outputText = series.toString();



        }
    }
}

