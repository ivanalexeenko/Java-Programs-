package com.control;

import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


public class Application extends JFrame {
    public static final int DEFAULT_WIDTH = 650;
    public static final int DEFAULT_HEIGHT = 450;
    public static final String FILENAME = "text.txt";

    public static final String FONT = "Algerian";
    public static final int FONT_STYLE = Font.BOLD;
    public static final int FONT_SIZE = 14;

    private JMenu menu;
    private JMenuItem menuItem;
    private JMenuBar menuBar;

    private JList fromAngleList;
    private DefaultListModel fromAngleListModel;
    private JTextField pointX1;
    private JTextField pointX2;
    private JTextField pointY1;
    private JTextField pointY2;



    private JMenuItem saveItem;
    private JMenu saveMenu;

    private JMenuItem dataItemOne;
    private JMenu dataMenu;


    private JTabbedPane tabbedPane;
    private JPanel panelOne;
    private JPanel panelTwo;
    private JPanel panelThree;

    private JScrollPane scrollPane;
    private JList list;
    private DefaultListModel listModel;
    private JFileChooser openFileChooser;

    private Font font;

    private Figures figures;

    private void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = screenSize.width / 2 - DEFAULT_WIDTH / 2;
        int y = screenSize.height / 2 - DEFAULT_HEIGHT / 2;
        setBounds(x,y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
    private void refreshListModel(DefaultListModel listModel,Figures figures) {
        listModel.clear();
        Iterator iterator = figures.iterator();
        while(iterator.hasNext()) {
            listModel.insertElementAt(iterator.next(),listModel.size());
        }
    }


    public Application() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setScreenBounds();

        setLayout(new BorderLayout());
        font = new Font(FONT,FONT_STYLE,FONT_SIZE);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menu = new JMenu("Open");
        menuItem = new JMenuItem("Text File...");
        menu.add(menuItem);
        menuBar.add(menu);

        figures = new Figures();

        File file = new File(FILENAME);
        try {
            figures.read(file);
        } catch (FileNotFoundException e) {
            String message = "No such file here,sorry for that";
            JOptionPane.showMessageDialog(null,message);
            System.out.println(message);
        }
        catch (NumberFormatException e) {
            String message = "Error: input type mismatch";
            JOptionPane.showMessageDialog(null,message);
            System.out.println(message);
        }
        catch (NoSuchElementException e) {
            String message = "Error: Inappropriate amount of arguments";
            JOptionPane.showMessageDialog(null,message);
            System.out.println(message);
        }


        Figures sortedFigures = new Figures();
        sortedFigures = figures;

        sortedFigures.sort();
        System.out.println("Sorted Figures:");
        Iterator iterator = sortedFigures.iterator();
        while(iterator.hasNext()) {
            Figure figure = (Figure) iterator.next();
            System.out.println(figure.toString());
        }

        listModel = new DefaultListModel();
        list = new JList(listModel);
        scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        list.setFont(font);

        add(scrollPane,BorderLayout.NORTH);

        refreshListModel(listModel, figures);

        openFileChooser = new JFileChooser();

        CustomListener listener = new CustomListener();
        menuItem.addActionListener(listener);

        saveItem = new JMenuItem("To File...");
        saveMenu = new JMenu("Save");
        saveMenu.add(saveItem);

        menuBar.add(saveMenu);
        saveItem.addActionListener(listener);

        dataMenu = new JMenu("Data");
        dataItemOne = new JMenuItem("From angle");
        dataMenu.add(dataItemOne);
        dataItemOne.addActionListener(listener);
        menuBar.add(dataMenu);


        fromAngleListModel = new DefaultListModel();
        fromAngleList = new JList(fromAngleListModel);
        pointX1 = new JTextField();
        pointX2 = new JTextField();
        pointY1 = new JTextField();
        pointY2 = new JTextField();

        pointX1.setFont(font);
        pointX2.setFont(font);
        pointY1.setFont(font);
        pointY2.setFont(font);

        panelOne = new JPanel();
        add(panelOne);

        GridLayout gridLayout = new GridLayout(1,4);
        panelOne.setLayout(gridLayout);

        panelOne.add(pointX1);
        panelOne.add(pointX2);
        panelOne.add(pointY1);
        panelOne.add(pointY2);



    }
    public class CustomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == menuItem) {
                if(openFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        figures.read(openFileChooser.getSelectedFile());
                        refreshListModel(listModel, figures);
                        String message = "Amount of Rectangles = " + figures.size();
                        System.out.println(message);
                        JOptionPane.showMessageDialog(null,message);

                    } catch (FileNotFoundException e1) {
                        String message = "File not found,sorry for that";
                        System.out.println(message);
                        JOptionPane.showMessageDialog(null,message);
                    }
                    catch (InputMismatchException e1) {
                        String message = "Wrong input format,check your data";
                        System.out.println(message);
                        JOptionPane.showMessageDialog(null,message);
                    }
                    catch (NoSuchElementException e1) {
                        String message = "Arguments amount mismatch";
                        System.out.println(message);
                        JOptionPane.showMessageDialog(null,message);
                    }

                }
            }
            else if(e.getSource() == saveItem) {
                if(openFileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileWriter writer = new FileWriter(openFileChooser .getSelectedFile());
                        BufferedWriter bufferedWriter = new BufferedWriter(writer);
                        Iterator iterator = figures.iterator();

                        while(iterator.hasNext()) {
                            Figure rectangle = (Figure) iterator.next();

                            bufferedWriter.write(rectangle.firstPoint.x + " ");
                            bufferedWriter.write(rectangle.firstPoint.y + " ");
                            bufferedWriter.write(rectangle.secondPoint.x + " ");
                            bufferedWriter.write(rectangle.secondPoint.y + " ");
                            bufferedWriter.newLine();
                        }
                        bufferedWriter.close();

                    } catch (IOException e1) {
                        // File will be created
                    }

                }
            }
            else if(e.getSource() == dataItemOne) {
                int x1 = 0;
                int x2 = 0;
                int y1 = 0;
                int y2 = 0;
                try {
                    x1 = Integer.parseInt(pointX1.getText());
                    x2 = Integer.parseInt(pointX2.getText());
                    y1 = Integer.parseInt(pointY1.getText());
                    y2 = Integer.parseInt(pointY2.getText());

                    StringBuffer message = new StringBuffer("From angle to angle points:");
                    message.append("(");
                    message.append(x1);
                    message.append(",");
                    message.append(x2);
                    message.append(") and ");

                    message.append("(");
                    message.append(y1);
                    message.append(",");
                    message.append(y2);
                    message.append(")");




                    System.out.println(message);
                    JOptionPane.showMessageDialog(null,message);

                }
                catch (NumberFormatException e1) {
                    String message = "Error point input,type mismatch!";
                    System.out.println(message);
                    JOptionPane.showMessageDialog(null,message);
                }


            }
        }
    }


}
