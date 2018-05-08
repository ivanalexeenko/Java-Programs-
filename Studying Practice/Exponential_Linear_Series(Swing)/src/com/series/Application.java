package com.series;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Application extends JFrame {
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

    public static String DEFAULT_CAPTION = "LALALALALA Series LALALALALA";
    public static final String FONT_STYLE = "Bernard MT Condensed";
    public static final String LINER_TYPE = "Liner";
    public static final String EXPONENTIAL_TYPE = "Exponential";
    public static final String FIRST_ELEMENT_TEXT = "First element:";
    public static final String COEFFICIENT_TEXT = "Coefficient:";
    public static final String AMOUNT_TEXT = "Amount:";
    public static final String FILE_TO_SAVE_TEXT = "File to save:";
    public static final String SUBMIT_TEXT = "Submit";
    public static final String MESSAGE_BOX_TEXT = "Wrong input,data will be replaced by default.";
    public static final String MESSAGE_BOX_SOFT_TEXT = "Series type is not chosen, Liner is default.";
    public static final String MESSAGE_BOX_AMOUNT_TEXT = "Amount of elements should be more than zero.";
    public static final String ICON_IMAGE = "apple.png";
    public static final String BACKGROUND_IMAGE = "background.jpg";
    public static final String SERIES_TYPE = "Choose a type of Series:";
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
    private JTextArea showSeries;
    private JButton submitButton;
    private JPanel panel;

    private Series series;

    private Double firstElement;
    private Integer amount;
    private Double coefficient;
    private String filename;
    private String outputText;
    private double sum;

    private void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width / 2) - (DEFAULT_WIDTH / 2);
        int y = (screenSize.height / 2) - (DEFAULT_HEIGHT / 2);
        setBounds(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);

    }
    private void setRadioButtonParameters(JRadioButton button,boolean isOpaque,boolean isEnabled,Color foregroundColor,Font font) {
        button.setOpaque(isOpaque);
        button.setEnabled(isEnabled);
        button.setForeground(foregroundColor);
        button.setFont(font);
    }
    private void addRadioButtonToPanel(JRadioButton button,JPanel panel,GridBagConstraints constraints,double weightx,int gridx,int gridy) {
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        constraints.weightx = weightx;
        panel.add(button,constraints);
    }
    private void setTextFieldParameters(JTextField textField,boolean isEnabled,boolean isOpaque,Font font) {
        textField.setEnabled(isEnabled);
        textField.setOpaque(isOpaque);
        textField.setFont(font);
    }
    private void addTextFieldToPanel(JTextField textField,JPanel panel,GridBagConstraints constraints,double weightx,int gridx,int gridy) {
        constraints.weightx = weightx;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        panel.add(textField,constraints);
    }
    private void addButtonToPanel(JButton button, JPanel panel,GridBagConstraints constraints,int gridx,int gridy,Font font) {
        button.setFont(font);
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        panel.add(button,constraints);

    }
    private void setTextAreaParameters(JTextArea textArea,boolean isEnabled,boolean isOpaque,Font font,int tabSize) {
            textArea.setTabSize(tabSize);
            textArea.setEnabled(isEnabled);
            textArea.setOpaque(isOpaque);
            textArea.setFont(font);
        }
    private void addTextAreaToPanel(JTextArea textArea,JPanel panel,GridBagConstraints constraints,double weightx,int gridx,int gridy) {
        constraints.weightx = weightx;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        panel.add(new JScrollPane(textArea),constraints);
    }

    private void locateAllFields(GridBagConstraints constraints,Color color,Font font) {
        linerRadioButton = new JRadioButton(LINER_TYPE);
        setRadioButtonParameters(linerRadioButton,false,true,color,font);
        addRadioButtonToPanel(linerRadioButton,panel,constraints,0.0,1,0);


        exponentialRadioButton = new JRadioButton(EXPONENTIAL_TYPE);
        setRadioButtonParameters(exponentialRadioButton,false,true,color,font);
        addRadioButtonToPanel(exponentialRadioButton,panel,constraints,0.0,3,0);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(linerRadioButton);
        buttonGroup.add(exponentialRadioButton);


        firstElementTextField = new JTextField(FIRST_ELEMENT_TEXT);
        setTextFieldParameters(firstElementTextField,false,false,font);
        addTextFieldToPanel(firstElementTextField,panel,constraints,0.0,0,1);

        firstElementInput = new JTextField();
        setTextFieldParameters(firstElementInput,true,true,font);
        addTextFieldToPanel(firstElementInput,panel,constraints,0.0,1,1);

        coefficientTextField = new JTextField(COEFFICIENT_TEXT);
        setTextFieldParameters(coefficientTextField,false,false,font);
        addTextFieldToPanel(coefficientTextField,panel,constraints,0.0,2,1);

        coefficientInput = new JTextField();
        setTextFieldParameters(coefficientInput,true,true,font);
        addTextFieldToPanel(coefficientInput,panel,constraints,0.0,3,1);

        amountTextField = new JTextField(AMOUNT_TEXT);
        setTextFieldParameters(amountTextField,false,false,font);
        addTextFieldToPanel(amountTextField,panel,constraints,0.0,0,2);

        amountInput = new JTextField();
        setTextFieldParameters(amountInput,true,true,font);
        addTextFieldToPanel(amountInput,panel,constraints,0.0,1,2);

        fileToSaveTextField = new JTextField(FILE_TO_SAVE_TEXT);
        setTextFieldParameters(fileToSaveTextField,false,false,font);
        addTextFieldToPanel(fileToSaveTextField,panel,constraints,0.0,2,2);

        fileToSaveInput = new JTextField();
        setTextFieldParameters(fileToSaveInput,true,true,font);
        addTextFieldToPanel(fileToSaveInput,panel,constraints,0.0,3,2);

        constraints.ipadx = BIG_BUTTON_WIDTH;
        constraints.ipady = BIG_BUTTON_HEIGHT;
        constraints.gridwidth = 4;

        showSeries = new JTextArea();
        setTextAreaParameters(showSeries,false,true,font,10);
        addTextAreaToPanel(showSeries,panel,constraints,0.0,0,3);


        submitButton = new JButton(SUBMIT_TEXT);
        addButtonToPanel(submitButton,panel,constraints,0,4,font);
    }
    Application() {
        super();
        setTitle(DEFAULT_CAPTION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Screen Size
        setScreenBounds();
        setResizable(false);

        GraphicalPanel graphicalPanel = new GraphicalPanel();
        panel = new JPanel();
        panel = graphicalPanel;
        add(panel);


        GridBagLayout gridBagLayout = new GridBagLayout();

        panel.setLayout(gridBagLayout);
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.ipadx = BUTTONS_WIDTH;
        constraints.ipady = BUTTONS_HEIGHT;

        Font font = new Font(FONT_STYLE,Font.ITALIC,FONT_SIZE);
        Color color = Color.blue;
        setFont(font);

        //Set Window Icon
        Image image = new ImageIcon(Application.class.getResource(ICON_IMAGE)).getImage();
        setIconImage(image);

        locateAllFields(constraints,color,font);


        Application.ApplicationListener submitButtonAction = new Application.ApplicationListener();
        submitButton.addActionListener(submitButtonAction);


    }
    public class ApplicationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                firstElement = Double.parseDouble(firstElementInput.getText());
                System.out.println(firstElement);
                amount = Integer.parseInt(amountInput.getText());
                coefficient = Double.parseDouble(coefficientInput.getText());
                filename = fileToSaveInput.getText();
                if(linerRadioButton.isSelected()) {
                    series = new Liner(firstElement.doubleValue(),coefficient.doubleValue());
                }
                else if(exponentialRadioButton.isSelected()) {
                    series = new Exponential(firstElement.doubleValue(),coefficient.doubleValue());
                }
                else {
                    throw new MyException(MESSAGE_BOX_SOFT_TEXT);
                }
            }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null,
                        MESSAGE_BOX_TEXT
                                + "(first = "
                                + EXAMPLE_FIRST_ELEMENT
                                + "&&coef = "
                                + EXAMPLE_COEFFICIENT
                                + "&&amount = "
                                + EXAMPLE_INDEX
                                + "&&filename:"
                                + EXAMPLE_FILENAME);
                firstElement = EXAMPLE_FIRST_ELEMENT;
                amount = EXAMPLE_INDEX;
                coefficient = EXAMPLE_COEFFICIENT;
                series = new Liner(firstElement.doubleValue(),coefficient.doubleValue());
                filename = EXAMPLE_FILENAME;
            }
            catch (MyException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
                series = new Liner(firstElement.doubleValue(),coefficient.doubleValue());
            }

            try {
                sum = series.sum(amount);
                if(amount <= 0) throw new MyException(MESSAGE_BOX_AMOUNT_TEXT);
            }
            catch (MyException e1) {
                JOptionPane.showMessageDialog(null,e1.getMessage());
            }

            outputText = series.toString();
            try {
                if(filename.length() == 0) {
                    filename = EXAMPLE_FILENAME;
                }
                series.fileOutput(filename);
            } catch (IOException e1) {
                // Just Create This File
            }
            showSeries.setText(outputText);
        }
    }


}
