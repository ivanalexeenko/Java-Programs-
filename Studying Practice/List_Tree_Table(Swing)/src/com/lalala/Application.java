package com.lalala;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Application extends JFrame {
    public static final int WIDTH = 850;
    public static final int HEIGHT = 550;
    public static final String CAPTION = "Flags,Countries and Capitals";
    public static final String FONT_NAME = "Bernard MT";
    public static final int FONT_SIZE = 40;
    public static final int SMALL_FONT_SIZE = 15;
    public static final int FONT_TYPE = Font.BOLD;

    private Map<String,Country> countries;
    private ArrayList<String> flagsPaths;

    private Font font;
    private JTabbedPane tabbedPane;
    private JPanel listPanel;
    private JScrollPane listScrollPane;
    private JList list;
    private DefaultListModel listModel;
    private JLabel flagLabel;
    private JTextField countryField;
    private JTextField capitalField;
    private JTextField flagFieldText;
    private JTextField countryFieldText;
    private JTextField capitalFieldText;
    private final String FLAG_TEXT = "Flag:";
    private final String COUNTRY_TEXT = "Country:";
    private final String CAPITAL_TEXT = "Capital:";
    private int selectedIndex;



    private char euro = 8364;
    private JPanel tablePanel;
    private JTable table;
    private String[] columnNames = {"Country","Description","Cost(" + euro + ")","Choose"};
    private Object[][] data;
    private JScrollPane tableScrollPane;
    private ArrayList<Tour> tourList;
    private int totalCost = 0;
    private DefaultTableModel tableModel;
    private JButton addButton = new JButton("Add");
    private JButton editButton = new JButton("Edit");
    private JPanel addEditPanel;
    private JTextField addCountry = new JTextField();
    private JTextField editCountry = new JTextField();
    private JTextField addDescription = new JTextField();
    private JTextField editDescription = new JTextField();
    private JTextField addCost = new JTextField();
    private JTextField editCost = new JTextField();
    private Font smallFont;

    private JPanel treePanel;
    private JTree tree;
    private String root = "FAMCS";
    private String[] nodes = {"Courses","Groups"};
    private ArrayList<Student> students;
    private String[] courses = {"1th","2nd","3rd","4th"};
    private Integer[] groups = {1,2,3,4,5,6,7};

    private JButton addStudentButton = new JButton("Add");
    private JPanel addEditStudentPanel;
    private JTextField addStudentName = new JTextField("Name");
    private JTextField addStudentCourse = new JTextField("Course");
    private JTextField addStudentGroup = new JTextField("Group");

    private JScrollPane treeScrollPane;
    private DefaultTreeModel treeModel;

    private DefaultMutableTreeNode[] groupNodes;
    private DefaultMutableTreeNode rootNode;

    private JDialog editDialog;
    private JButton editStudentButton;
    private JTextField dialogName;
    private JTextField dialogCourse;
    private JTextField dialogGroup;
    private JButton dialogSubmit;
    private JButton deleteSelected;


    private int xCenter;
    private  int yCenter;
    private int dialogWidth = 400;
    private  int dialogHeight = 250;

    public void refreshTotal() {
        totalCost = 0;
        for(int i = 0;i < table.getRowCount() - 1;i++) {
            if((Boolean)table.getValueAt(i,table.getColumnCount() - 1)) {
                totalCost += (Integer)table.getValueAt(i,table.getColumnCount() - 2);
            }
        }

        table.setValueAt(totalCost,table.getRowCount() - 1,table.getColumnCount() - 2);
    }

    public void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (int)screenSize.getWidth() / 2 - Application.WIDTH / 2;
        int y = (int)screenSize.getHeight() / 2 - Application.HEIGHT / 2;
        xCenter = (int)screenSize.getWidth() / 2 - dialogWidth / 2;
        yCenter = (int)screenSize.getHeight() / 2 - dialogHeight / 2;
        setBounds(x,y,Application.WIDTH,Application.HEIGHT);
    }
    Application() {
        setScreenBounds();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Application.CAPTION);
        font = new Font(FONT_NAME,FONT_TYPE,FONT_SIZE);
        setFont(font);
        countries = new TreeMap<>();
        Scanner scanner = null;
        flagsPaths = new ArrayList<String>();
        try {
            scanner = new Scanner(new File("flags.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNextLine()) {
            String tempo = scanner.nextLine();
            flagsPaths.add(tempo);
        }

        try {
            scanner = new Scanner(new File("countries.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int index = 0;
        while(scanner.hasNextLine()) {
            String country = new String();
            String capital = new String();
            String tempo = scanner.nextLine();
            country = tempo.substring(0,tempo.indexOf("-") - 1);
            capital = tempo.substring(tempo.indexOf("-") + 1);
            countries.put(country,new Country(country,capital,flagsPaths.get(index)));
            index++;
        }


        ///////////////////////// FIRST PANEL: ///////////////////////////////////////////
        listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.setName("List of Countries");
        listModel = new DefaultListModel();
        index = 0;
        for( Map.Entry<String,Country> entry : countries.entrySet()) {
            listModel.add(index,entry.getValue());
            index++;
        }


        list = new JList(listModel);
        list.setFont(font);

        list.setCellRenderer(new CountryImagesRenderer());
        FlagSelectionListener listener = new FlagSelectionListener();
        list.addListSelectionListener(listener);

        setLayout(new BorderLayout());
        listScrollPane = new JScrollPane(list);
        listScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        listScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        listPanel.add(listScrollPane,BorderLayout.WEST);
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(3,2));

        flagFieldText = new JTextField();
        flagFieldText.setFont(font);
        flagFieldText.setText(FLAG_TEXT);
        flagFieldText.setEditable(false);
        flagFieldText.setHorizontalAlignment(JTextField.CENTER);

        countryFieldText = new JTextField();
        countryFieldText.setFont(font);
        countryFieldText.setText(COUNTRY_TEXT);
        countryFieldText.setEditable(false);
        countryFieldText.setHorizontalAlignment(JTextField.CENTER);

        capitalFieldText = new JTextField();
        capitalFieldText.setFont(font);
        capitalFieldText.setText(CAPITAL_TEXT);
        capitalFieldText.setEditable(false);
        capitalFieldText.setHorizontalAlignment(JTextField.CENTER);


        flagLabel = new JLabel();
        flagLabel.setHorizontalAlignment(JLabel.CENTER);



        countryField = new JTextField();
        countryField.setFont(font);
        countryField.setEditable(false);
        countryField.setHorizontalAlignment(JTextField.CENTER);
        JScrollPane countryPane = new JScrollPane(countryField);
        countryPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        capitalField = new JTextField();
        capitalField.setFont(font);
        capitalField.setEditable(false);
        capitalField.setHorizontalAlignment(JTextField.CENTER);
        JScrollPane capitalPane = new JScrollPane(capitalField);
        countryPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        labelPanel.add(flagFieldText);
        labelPanel.add(flagLabel);
        labelPanel.add(countryFieldText);
        labelPanel.add(countryPane);
        labelPanel.add(capitalFieldText);
        labelPanel.add(capitalPane);

        listPanel.add(labelPanel,BorderLayout.CENTER);




        tabbedPane = new JTabbedPane();
        add(tabbedPane);
        tabbedPane.add(listPanel);

        //////////////////////////////////// SECOND PANEL: /////////////////////////////////////

        tourList = new ArrayList<Tour>();
        try {
            scanner = new Scanner(new File("tourData.txt"));
            while(scanner.hasNextLine()) {
                String countryName = scanner.nextLine();
                Country country = countries.get(countryName);
                ImageIcon icon = new ImageIcon(getClass().getResource("Flags/" + country.getFlagPath()));
                String description = scanner.nextLine();
                String cost = scanner.nextLine();
                Integer money = Integer.parseInt(cost);
                Tour tour = new Tour(icon,description,money,new Boolean(false));
                tourList.add(tour);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        data = new Object[tourList.size()][columnNames.length];
        for(int i = 0;i < tourList.size();i++) {
            data[i][0] = tourList.get(i).getIcon();
            data[i][1] = tourList.get(i).getDescription();
            data[i][2] = tourList.get(i).getCost();
            data[i][3] = tourList.get(i).getCheck();
        }



        tablePanel = new JPanel();
        tablePanel.setName("Take a Tour");
        tablePanel.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel(data, columnNames)
        {
            @Override
            public Class getColumnClass(int column)
            {
                return getValueAt(0, column).getClass();
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == columnNames.length - 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(new ImageIcon(getClass().getResource("Flags/" + "flag_afghanistan.png")).getIconHeight());
        Object[] totalColumn = {null,"Total Cost:",Integer.valueOf(0),null};
        tableModel.addRow(totalColumn);
        TableRenderer renderer = new TableRenderer();
        table.setDefaultRenderer(Integer.class,renderer);
        table.setDefaultRenderer(String.class,renderer);
        table.setDefaultRenderer(ImageIcon.class,renderer);

        ListSelectionModel listSelectionModel = table.getSelectionModel();
        TableSelectionListener tableSelectionListener = new TableSelectionListener();
        listSelectionModel.addListSelectionListener(tableSelectionListener);

        tableScrollPane = new JScrollPane(table);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addEditPanel = new JPanel();
        addEditPanel.setLayout(new GridLayout(2,4));
        smallFont = new Font(FONT_NAME,FONT_TYPE,SMALL_FONT_SIZE);
        addCountry.setHorizontalAlignment(JTextField.CENTER);
        addCountry.setFont(smallFont);
        addDescription.setHorizontalAlignment(JTextField.CENTER);
        addDescription.setFont(smallFont);
        addCost.setHorizontalAlignment(JTextField.CENTER);
        addCost.setFont(smallFont);
        editCountry.setHorizontalAlignment(JTextField.CENTER);
        editCountry.setFont(smallFont);
        editDescription.setHorizontalAlignment(JTextField.CENTER);
        editDescription.setFont(smallFont);
        editCost.setHorizontalAlignment(JTextField.CENTER);
        editCost.setFont(smallFont);



        addEditPanel.add(addCountry);
        addEditPanel.add(addDescription);
        addEditPanel.add(addCost);
        addEditPanel.add(addButton);
        addEditPanel.add(editCountry);
        addEditPanel.add(editDescription);
        addEditPanel.add(editCost);
        addEditPanel.add(editButton);

        AddEditButtonListener buttonListener = new AddEditButtonListener();
        addButton.addActionListener(buttonListener);
        editButton.addActionListener(buttonListener);


        tablePanel.add(tableScrollPane,BorderLayout.NORTH);
        tablePanel.add(addEditPanel,BorderLayout.SOUTH);
        tabbedPane.add(tablePanel);

        /////////////////////////////THIRD PANEL: //////////////////////////////////////////

        students = new ArrayList<>();
        try {
            scanner = new Scanner(new File("students.txt"));
            while(scanner.hasNext()) {
                Integer course = 0;
                Integer group = 0;
                String[] nfs = new String[3];
                course = scanner.nextInt();
                group = scanner.nextInt();
                nfs[0] = scanner.next();
                nfs[1] = scanner.next();
                nfs[2] = scanner.next();
                Student student = new Student(course,group,nfs);
                students.add(student);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



        treePanel = new JPanel();
        rootNode = new DefaultMutableTreeNode(root); // 0 level
        DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(nodes[0]); // 1 level
        rootNode.add(courseNode);
        groupNodes = new DefaultMutableTreeNode[courses.length]; // 2 level
        for(int i = 0; i < groupNodes.length;i++) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(nodes[1]);
            for(int j = 0;j < groups.length;j++) {
                node.add(new DefaultMutableTreeNode(groups[j]));
            }
            groupNodes[i] = node;
        }



        for(int i = 0;i < courses.length;i++) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(courses[i]);
            node.add(groupNodes[i]);
            courseNode.add(node);
        }

        for(int i = 0;i < students.size();i++) {
            Integer course = students.get(i).getCourse();
            Integer group = students.get(i).getGroup();
            String[] nfs = students.get(i).getNFS();
            StringBuffer buffer = new StringBuffer("");
            for(int j = 0;j < nfs.length;j++) {
                buffer.append(nfs[j]);
                buffer.append(" ");
            }
            buffer.deleteCharAt(buffer.length() - 1);
            DefaultMutableTreeNode node =  (DefaultMutableTreeNode)groupNodes[course - 1].getChildAt(group - 1);
            node.add(new DefaultMutableTreeNode(buffer.toString()));
        }
        addEditStudentPanel = new JPanel();
        addEditStudentPanel.setLayout(new GridLayout(2,4));

        addEditStudentPanel.add(addStudentName);
        addEditStudentPanel.add(addStudentCourse);
        addEditStudentPanel.add(addStudentGroup);
        addEditStudentPanel.add(addStudentButton);

        addStudentButton.addActionListener(buttonListener);

        treeModel = new DefaultTreeModel(rootNode);
        tree = new JTree(treeModel);
        treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        treeScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        ImageIcon rootIcon = new ImageIcon(getClass().getResource("Emojies/" + "root.png"));
        ImageIcon courseIcon = new ImageIcon(getClass().getResource("Emojies/" + "course.png"));
        ImageIcon groupIcon = new ImageIcon(getClass().getResource("Emojies/" + "group.png"));
        ImageIcon studentIcon = new ImageIcon(getClass().getResource("Emojies/" + "student.png"));
        ImageIcon groupNumberIcon = new ImageIcon(getClass().getResource("Emojies/" + "group_number.png"));
        ImageIcon groupSecondIcon = new ImageIcon(getClass().getResource("Emojies/" + "group_second.png"));

        TreeCellRenderer treeCellRenderer = new TreeCellRenderer(rootIcon,courseIcon,groupNumberIcon,groupIcon,groupSecondIcon,studentIcon);
        tree.setCellRenderer(treeCellRenderer);

        treePanel.setLayout(new BorderLayout());
        treePanel.add(treeScrollPane,BorderLayout.NORTH);
        treePanel.add(addEditStudentPanel,BorderLayout.SOUTH);
        treePanel.setName("Tree of Students");
        editStudentButton = new JButton("Edit");
        editStudentButton.addActionListener(buttonListener);
        addEditStudentPanel.add(new JLabel());
        addEditStudentPanel.add(new JLabel());
        addEditStudentPanel.add(new JLabel());
        addEditStudentPanel.add(editStudentButton);

        editDialog = new JDialog();
        editDialog.setTitle("Student Data Editor");
        editDialog.setLayout(new BorderLayout());
        dialogName = new JTextField();
        dialogName.setHorizontalAlignment(JTextField.CENTER);
        dialogCourse = new JTextField();
        dialogCourse.setHorizontalAlignment(JTextField.CENTER);
        dialogGroup = new JTextField();
        dialogGroup.setHorizontalAlignment(JTextField.CENTER);
        dialogSubmit = new JButton("Submit");
        deleteSelected = new JButton("Delete Selected");

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridLayout(1,4));
        dialogPanel.add(dialogName);
        dialogPanel.add(dialogCourse);
        dialogPanel.add(dialogGroup);
        dialogPanel.add(dialogSubmit);
        editDialog.add(dialogPanel,BorderLayout.NORTH);
        editDialog.add(deleteSelected,BorderLayout.SOUTH);

        editDialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        editDialog.setBounds(xCenter,yCenter,dialogWidth,dialogHeight);

        DialogButtonListener dialogButtonListener = new DialogButtonListener();
        dialogSubmit.addActionListener(dialogButtonListener);
        deleteSelected.addActionListener(dialogButtonListener);


        tabbedPane.add(treePanel);
    }

    private void deleteSelectedNodes() {
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        TreePath[] paths = tree.getSelectionPaths();
        if(paths != null) {
            for(TreePath path : paths) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                if (node.getParent() != null && node.getLevel() == 5) {
                    model.removeNodeFromParent(node);
                }
            }
        }

    }

    public class FlagSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            selectedIndex = list.getSelectedIndex();

            Country country = countries.get("Belarus");
            country = (Country) list.getSelectedValue();

            countryField.setText(country.getName());
            capitalField.setText(country.getCapital());
            ImageIcon icon = new ImageIcon(getClass().getResource("Flags/" + country.getFlagPath()));
            flagLabel.setIcon(icon);
        }
    }
    public class TableRenderer implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            JLabel label = new JLabel();
            if (value != null) {
                if(value instanceof ImageIcon) {
                    label.setIcon((ImageIcon)value);
                }
                else {
                    label.setText(value.toString());
                }

            }
            label.setHorizontalAlignment(JLabel.CENTER);
            return label;
        }

    }
    public class TableSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            totalCost = 0;
            for(int i = 0;i < table.getRowCount() - 1;i++) {
                if((Boolean)table.getValueAt(i,table.getColumnCount() - 1)) {
                    totalCost += (Integer)table.getValueAt(i,table.getColumnCount() - 2);
                }
            }

            table.setValueAt(totalCost,table.getRowCount() - 1,table.getColumnCount() - 2);
            refreshTotal();
        }
    }
    public class AddEditButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String count = "";
            String description = "";
            Integer cost = 0;
            if(e.getSource() == addButton) {
                count = addCountry.getText();
                description = addDescription.getText();
                Country country = countries.get(count);
                if(country == null) {
                    JOptionPane.showMessageDialog(null,"Sorry,This Country: (" + "\"" + count + "\"" +  ") Not Found");
                }
                else {
                    try{
                        cost = Integer.parseInt(addCost.getText());
                        Object[] newRow = {new ImageIcon(getClass().getResource("Flags/" + country.getFlagPath())),description,cost,false};

                        int row = -1;
                        for(int i = 0;i < tableModel.getRowCount() - 1;i++) {
                            String temp = (tableModel.getValueAt(i,0).toString());
                            temp = temp.substring(temp.indexOf("Flags/"),temp.length());
                            if(temp.compareTo("Flags/" + country.getFlagPath()) == 0) {
                                row = i;
                            }
                        }

                        if(row == -1) {
                            tableModel.insertRow(tableModel.getRowCount() - 1,newRow);
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"This Country has been already added");
                        }



                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,"Wrong Cost Format Input");
                    }

                }


            }
            else if(e.getSource() == editButton) {
                count = editCountry.getText();
                description = editDescription.getText();
                Country country = countries.get(count);
                if(country == null) {
                    JOptionPane.showMessageDialog(null,"Sorry,This Country: (" + "\"" + count + "\"" +  ") Not Found");
                }
                else {
                    try{
                        cost = Integer.parseInt(editCost.getText());
                        int row = -1;
                        for(int i = 0;i < tableModel.getRowCount() - 1;i++) {
                            String temp = (tableModel.getValueAt(i,0).toString());
                            temp = temp.substring(temp.indexOf("Flags/"),temp.length());
                            if(temp.compareTo("Flags/" + country.getFlagPath()) == 0) {
                                row = i;
                            }
                        }

                        if(row == -1) {
                            JOptionPane.showMessageDialog(null,"Sorry,This Country: (" + "\"" + count + "\"" +  ") Not Found In The Table");
                        }
                        else {
                            Object[] newRow = {tableModel.getValueAt(row,0),description,cost,tableModel.getValueAt(row,columnNames.length - 1)};
                            for(int i = 0;i < columnNames.length;i++) {
                                tableModel.setValueAt(newRow[i],row,i);
                            }
                        }
                        refreshTotal();



                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null,"Wrong Cost Format Input");
                    }

                }


        }
        else if(e.getSource() == addStudentButton) {
                String name = addStudentName.getText();
                if(name.length() == 0) {
                    JOptionPane.showMessageDialog(null,"Student's name is Empty");
                }
                else {
                    String[] nfs = new String[3];
                    StringTokenizer tokenizer = new StringTokenizer(name);
                    int i = 0;
                    while(tokenizer.hasMoreTokens()) {
                        nfs[i] = tokenizer.nextToken();
                        i++;
                    }
                    try {
                        Integer course = Integer.parseInt(addStudentCourse.getText());
                        Integer group = Integer.parseInt(addStudentGroup.getText());
                        if(course < 0 || course > 4 || group < 0 || group > 7) {
                            JOptionPane.showMessageDialog(null,"Course or Group Out of Range");
                        }
                        else {

                            DefaultMutableTreeNode node =(DefaultMutableTreeNode)groupNodes[course - 1].getChildAt(group - 1);
                            StringBuffer buffer = new StringBuffer("");
                            for(int k = 0;k < 3;k++) {
                                buffer.append(nfs[k]);
                                buffer.append(" ");
                            }
                            buffer.deleteCharAt(buffer.length() - 1);

                            node.add(new DefaultMutableTreeNode(buffer.toString()));
                            treeModel.reload(node);

                        }

                    }
                    catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null,"Wrong Number Input Format");
                    }



                }

            }
            else if(e.getSource() == editStudentButton) {
                editDialog.setVisible(true);
            }

    }




}
    public class TreeCellRenderer extends DefaultTreeCellRenderer {
        private ImageIcon icon1;
        private ImageIcon icon2;
        private ImageIcon icon3;
        private ImageIcon icon4;
        private ImageIcon icon5;
        private ImageIcon icon6;

        public TreeCellRenderer(ImageIcon i1,ImageIcon i2,ImageIcon i3,ImageIcon i4,ImageIcon i5,ImageIcon i6) {
            icon1 = i1;
            icon2 = i2;
            icon3 = i3;
            icon4 = i4;
            icon5 = i5;
            icon6 = i6;

        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            switch(((DefaultMutableTreeNode)value).getLevel()) {
                case 0: {
                    setIcon(icon1);
                    break;
                }
                case 1: {
                    setIcon(icon2);
                    break;
                }
                case 2: {
                    setIcon(icon3);
                    break;
                }
                case 3: {
                    setIcon(icon4);
                    break;
                }
                case 4: {
                    setIcon(icon5);
                    break;
                }
                case 5: {
                    setIcon(icon6);
                }
            }
            return this;

        }
    }
    public class DialogButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == deleteSelected) {
                deleteSelectedNodes();
            }
            else if(e.getSource() == dialogSubmit) {
                String name;
                Integer course;
                Integer group;
                name = dialogName.getText();
                if(name.length() == 0) {
                    JOptionPane.showMessageDialog(null, "Name is Empty");
                }
                else {
                    try{
                        course = Integer.parseInt(dialogCourse.getText());
                        group = Integer.parseInt(dialogGroup.getText());
                        if(course < 0 || course > 4 || group < 0 || group > 7) {
                            JOptionPane.showMessageDialog(null,"Course or Group does not exist!");
                        }
                        else {
                            deleteSelectedNodes();
                            DefaultMutableTreeNode node =  (DefaultMutableTreeNode)groupNodes[course - 1].getChildAt(group - 1);
                            node.add(new DefaultMutableTreeNode(name));
                            treeModel.reload(node);
                        }
                    }
                    catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null,"Wrong Input Format");
                    }

                }
            }

        }
    }

}



