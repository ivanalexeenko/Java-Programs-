package com.session;

import org.w3c.dom.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Application extends JFrame {

    public static final String FONT_NAME = "Algerian";
    public static final int FONT_STYLE = Font.BOLD;
    public static final int FONT_SIZE = 20;
    public static final int DEFAULT_WIDTH = 750;
    public static final int DEFAULT_HEIGHT = 550;
    public static final int ROWS = 4;
    public static final int COLUMNS = 3;
    private Font font;
    private JFileChooser fileChooser;
    private JMenu menu;
    private JMenuItem menuItem;
    private JMenuBar menuBar;
    private JTextField numberInputField;
    private JButton addSessionButton;
    private JTextField surnameField;
    private JTextField surnameInputField;
    private JTextField subjectsText;
    private JTextField subjectField;
    private JTextField subjectInputField;
    private JTextField markField;
    private JTextField markInputField;
    private JTextField addedField;
    private Session session;
    private DefaultListModel listModel;
    private JList list;
    private JScrollPane scrollPane;
    private JTextField numberField;
    private JMenu save;
    private JMenuItem saveItem;
    private JMenu read;
    private JMenuItem readItem;

    private JMenu sessionTableMenu;
    private JMenuItem sessionTableMenuItem;
    private DefaultTableModel defaultTableModel;
    private JTable table;
    private JDialog tableDialog;

    private Object[] columnNames = {"Number","Surname","Subject","Mark"};
    private Object[][] data;
    private JButton removeButton;


    private void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = screenSize.width / 2 - DEFAULT_WIDTH / 2;
        int y = screenSize.height / 2 - DEFAULT_HEIGHT / 2;
        setBounds(x,y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
    private void initializeFields() {
        numberField = new JTextField("Number:");
        numberField.setFont(font);
        numberField.setEditable(false);
        add(numberField);

        numberInputField = new JTextField();
        numberInputField.setFont(font);
        add(numberInputField);

        addSessionButton = new JButton("Add SessionData");
        addSessionButton.setFont(font);
        add(addSessionButton);

        surnameField = new JTextField("Surname:");
        surnameField.setFont(font);
        surnameField.setEditable(false);
        add(surnameField);

        surnameInputField = new JTextField();
        surnameInputField.setFont(font);
        add(surnameInputField);

        subjectsText = new JTextField("List of Subjects:");
        subjectsText.setEditable(false);
        subjectsText.setFont(font);
        add(subjectsText);


        subjectField = new JTextField("Subject:");
        subjectField.setFont(font);
        subjectField.setEditable(false);
        add(subjectField);

        subjectInputField = new JTextField();
        subjectInputField.setFont(font);
        add(subjectInputField);

        add(scrollPane);

        markField = new JTextField("Mark:");
        markField.setFont(font);
        markField.setEditable(false);
        add(markField);

        markInputField = new JTextField();
        markInputField.setFont(font);
        add(markInputField);

        addedField = new JTextField();
        addedField.setFont(font);
        addedField.setEditable(false);
        addedField.setHorizontalAlignment(JTextField.CENTER);
        add(addedField);


        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menu = new JMenu("Open");
        menuItem = new JMenuItem("File");
        menu.add(menuItem);
        menuBar.add(menu);

        save = new JMenu("Save to XML");
        saveItem = new JMenuItem("Choose path");
        save.add(saveItem);
        menuBar.add(save);

        read = new JMenu("Read from XML");
        readItem = new JMenuItem("Choose path");
        read.add(readItem);
        menuBar.add(read);

        sessionTableMenu = new JMenu("Session Table");
        sessionTableMenuItem = new JMenuItem("See and Edit Table");
        sessionTableMenu.add(sessionTableMenuItem);
        menuBar.add(sessionTableMenu);

        fileChooser = new JFileChooser();

    }
    public void refreshListModelData(DefaultListModel listModel,Session session) {
        listModel.clear();
        ArrayList arrayList = new ArrayList<>();
        arrayList = session.getSubjects();
        Iterator<SessionData> iterator = arrayList.iterator();
        while(iterator.hasNext()) {
            if(listModel.size() == 0) {
                listModel.add(0,iterator.next());
            }
            else {
                listModel.insertElementAt(iterator.next(),listModel.size());
            }
        }
    }
    public void refreshTableModelData(DefaultTableModel defaultTableModel,Session session) {
        for(int i = defaultTableModel.getRowCount() - 1;i >= 0;i--) {
            defaultTableModel.removeRow(i);
        }
        ArrayList<SessionData> arrayList = new ArrayList<>();
        arrayList = session.getSessionDataList();
        Iterator<SessionData> iterator = arrayList.iterator();
        while(iterator.hasNext()) {
            addTableModelRow(defaultTableModel,iterator.next());
        }

    }
    public void addTableModelRow(DefaultTableModel defaultTableModel,SessionData sessionData) {
        Object[] addedRow = new Object[columnNames.length];
        addedRow[0] = sessionData.getNumber();
        addedRow[1] = sessionData.getSurname();
        addedRow[2] = sessionData.getSubject();
        addedRow[3] = sessionData.getMark();
        defaultTableModel.addRow(addedRow);
    }
    public Application()  {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setScreenBounds();

        setLayout(new BorderLayout());

        session = new Session();

        try {
            session.read(Main.DEFAULT_FILENAME);
        } catch (IOException e) {
            System.out.println("Wrong input,data will be replaced by default");
            SessionData sessionData = new SessionData(Main.DEFAULT_NUMBER,Main.DEFAULT_SURNAME,Main.DEFAULT_SUBJECT,Main.DEFAULT_MARK);
            session.add(sessionData);
        }
        catch(NoSuchElementException e) {
            System.out.println("No such element here,data will be replaced by default");
            SessionData sessionData = new SessionData(Main.DEFAULT_NUMBER,Main.DEFAULT_SURNAME,Main.DEFAULT_SUBJECT,Main.DEFAULT_MARK);
            session.add(sessionData);
        }
        catch (NumberFormatException e) {
            System.out.println("Wrong data format,data will be replaced by default");
            SessionData sessionData = new SessionData(Main.DEFAULT_NUMBER,Main.DEFAULT_SURNAME,Main.DEFAULT_SUBJECT,Main.DEFAULT_MARK);
            session.add(sessionData);
        }

        listModel = new DefaultListModel();
        refreshListModelData(listModel,session);


        list = new JList(listModel);

        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        list.setAlignmentX(JList.CENTER_ALIGNMENT);

        font = new Font(FONT_NAME,FONT_STYLE,FONT_SIZE);

        list.setFont(font);

        GridLayout gridLayout = new GridLayout(ROWS,COLUMNS);
        setLayout(gridLayout);

        initializeFields();

        CustomListener listener = new CustomListener();
        addSessionButton.addActionListener(listener);
        menuItem.addActionListener(listener);
        saveItem.addActionListener(listener);
        readItem.addActionListener(listener);
        sessionTableMenuItem.addActionListener(listener);
        removeButton = new JButton("Remove From Table");
        removeButton.addActionListener(listener);

        tableDialog = new JDialog();
        tableDialog.setResizable(false);
        tableDialog.setBounds(getX() + 10,getY() + 10,getWidth(),getHeight());
        tableDialog.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BorderLayout());
        tableDialog.add(dialogPanel);

        ArrayList<SessionData> arrayList = session.getSessionDataList();

        data = new Object[arrayList.size()][columnNames.length];
        for(int i = 0;i < arrayList.size();i++) {
            data[i][0] = arrayList.get(i).getNumber();
            data[i][1] = arrayList.get(i).getSurname();
            data[i][2] = arrayList.get(i).getSubject();
            data[i][3] = arrayList.get(i).getMark();
        }

        defaultTableModel = new DefaultTableModel(data,columnNames) {
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            };

    };
        table = new JTable(defaultTableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        dialogPanel.add(scrollPane, BorderLayout.NORTH);

        dialogPanel.add(removeButton,BorderLayout.SOUTH);




    }
    public class CustomListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == addSessionButton) {
                try {
                    Integer num = Integer.parseInt(numberInputField.getText());
                    String sur = surnameInputField.getText();
                    String sub = subjectInputField.getText();
                    Integer mar = Integer.parseInt(markInputField.getText());
                    SessionData sessionData = new SessionData(num,sur,sub,mar);
                    session.add(sessionData);
                    addTableModelRow(defaultTableModel,sessionData);
                    addedField.setText("!GRANTED!");
                }
                catch (NumberFormatException e1) {
                    System.out.println(e1.getMessage());
                    System.out.println("Wrong input format, input denied");
                    JOptionPane.showMessageDialog(null,"Wrong input format,input denied");
                    addedField.setText("!DENIED!");
                }

                refreshListModelData(listModel,session);

            }
            else if(e.getSource() == menuItem) {
                if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        session.clear();
                        session.read(file.getAbsolutePath());
                        addedField.setText("!GRANTED!");

                    } catch (IOException e1) {
                        System.out.println("Wrong input format,input denied");
                        JOptionPane.showMessageDialog(null,"Wrong input format,input denied");
                        session.add((new SessionData(Main.DEFAULT_NUMBER,Main.DEFAULT_SURNAME,Main.DEFAULT_SUBJECT,Main.DEFAULT_MARK)));
                        addedField.setText("!DENIED!");
                    }
                    refreshListModelData(listModel,session);
                    refreshTableModelData(defaultTableModel,session);

                }
            }
            else if(e.getSource() == saveItem) {
                JFileChooser fc = new JFileChooser();
                if (fc.showSaveDialog(fc) == JFileChooser.CANCEL_OPTION) {
                    return;
                }
                File file = fc.getSelectedFile();
                String filePath = file.getPath();
                try {
                    FileWriter fw = new FileWriter(filePath + ".xml");
                    fw.write(session.toXMLString());
                    fw.close();

                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(Application.this, e1.getMessage(),
                            "Error writing to file" , JOptionPane.ERROR_MESSAGE);
                    System.out.println("Error writing to file");
                }
            }
            else if(e.getSource() == readItem) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    if(fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        session.readFromXML(file);
                        refreshListModelData(listModel,session);
                        refreshTableModelData(defaultTableModel,session);
                    }

                } catch (Exception e1) {
                    System.out.println("Error with reading occured");
                }
            }
            else if(e.getSource() == sessionTableMenuItem) {
                tableDialog.setVisible(true);
            }
            else if(e.getSource() == removeButton) {
                int[] indices = table.getSelectedRows();
                for(int i = indices.length - 1;i >= 0;i--) {
                    session.remove(indices[i]);
                    refreshListModelData(listModel,session);
                    defaultTableModel.removeRow(indices[i]);
                }
            }

            }


        }

    }
