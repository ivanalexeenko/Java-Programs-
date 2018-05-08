package com.windows;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Application extends JFrame {

    public static final int DEFAULT_WIDTH = 650;
    public static final int DEFAULT_HEIGHT = 450;
    public static final int ROWS = 10;
    public static final int COLUMNS = 10;

    public static final int RADIO_COL = 5;
    public static final int RADIO_ROW = 5;

    private JPanel firstPanel;

    private JList leftList;
    private JList rightList;
    private JScrollPane leftScrollPane;
    private JScrollPane rightScrollPane;
    private JButton toTheLeft;
    private JButton toTheRight;
    private JPanel centralPanel;
    private DefaultListModel<String> leftListModel;
    private DefaultListModel<String> rightListModel;

    private JTabbedPane tabbedPane;


    private JPanel secondPanel;

    private Color smileColor;
    private Color backColor;
    private Color changerColor;
    private int[] arraySmileIndexes = {3,13,23,33,43,6,16,26,36,46,61,72,83,84,85,86,77,68};

    private JPanel thirdPanel;
    ImageIcon uncheckedImage;
    ImageIcon checkedImage;
    ImageIcon waitImage;
    ImageIcon waitLongImage;
    public static final String UNCHECKED_PATH = "unchecked.png";
    public static final String CHECKED_PATH = "checked.png";
    public static final String WAIT_LONG_PATH = "waitLong.png";
    public static final String WAIT_PATH = "wait.png";

    public static final int DELAY = 2000;


    private void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = screenSize.width / 2 - DEFAULT_WIDTH / 2;
        int y = screenSize.height / 2 - DEFAULT_HEIGHT / 2;
        setBounds(x,y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
    private void fillLists() {
        leftListModel.add(0,"Barcelona");
        leftListModel.add(1,"Real Madrid");
        leftListModel.add(2,"Juventus");
        leftListModel.add(3,"Porto");
        leftListModel.add(4,"Sevilla");
        leftListModel.add(5,"Shahtar Donetsk");
        leftListModel.add(6,"Bayern Munich");
        leftListModel.add(7,"Basel");

        rightListModel.add(0,"Chelsea");
        rightListModel.add(1,"Paris Saint Germain");
        rightListModel.add(2,"Tottenham");
        rightListModel.add(3,"Liverpool");
        rightListModel.add(4,"Manchester United");
        rightListModel.add(5,"AC Roma");
        rightListModel.add(6,"Besiktas");
        rightListModel.add(7,"Manchester City");
    }
    private void addListeners() {
        ButtonListener buttonListener = new ButtonListener();
        toTheLeft.addActionListener(buttonListener);
        toTheRight.addActionListener(buttonListener);
    }

    public Application() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setScreenBounds();

        // First Program Window

        firstPanel = new JPanel();

        setLayout(new BorderLayout());
        firstPanel.setLayout(new BorderLayout());

        leftListModel = new DefaultListModel<String>();
        rightListModel = new DefaultListModel<String>();

        fillLists();

        leftList = new JList(leftListModel);
        rightList = new JList(rightListModel);

        leftScrollPane = new JScrollPane(leftList);
        leftScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        rightScrollPane = new JScrollPane(rightList);
        rightScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        firstPanel.add(leftScrollPane,BorderLayout.WEST);
        firstPanel.add(rightScrollPane,BorderLayout.EAST);

        centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());
        firstPanel.add(centralPanel,BorderLayout.CENTER);

        toTheLeft = new JButton("<<<");
        toTheRight = new JButton(">>>");

        centralPanel.add(toTheRight,BorderLayout.SOUTH);
        centralPanel.add(toTheLeft,BorderLayout.NORTH);

        addListeners();

        tabbedPane = new JTabbedPane();
        add(tabbedPane);
        tabbedPane.add("Lists",firstPanel);

        // Second Program Window

        secondPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(ROWS,COLUMNS);
        secondPanel.setLayout(gridLayout);

        smileColor = Color.RED;
        backColor = Color.WHITE;
        changerColor = Color.BLACK;


        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0;i < arraySmileIndexes.length;i++) {
            stack.push(arraySmileIndexes[i]);
        }
        stack.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });

        CustomMouseListener mouseListener = new CustomMouseListener();
        for(int i = 0;i < ROWS * COLUMNS;i++) {
            JButton button = new JButton();
            button.addMouseListener(mouseListener);
            if(!stack.isEmpty() && i == stack.peek()) {
                button.setBackground(smileColor);
                stack.pop();
            }
            else {
                button.setBackground(backColor);
            }
            secondPanel.add(button);
        }

        tabbedPane.add("Buttons",secondPanel);

        // Third Program Window

        thirdPanel = new JPanel();
        GridLayout gridLayout1 = new GridLayout(RADIO_ROW,RADIO_COL);
        thirdPanel.setLayout(gridLayout1);

        uncheckedImage = new ImageIcon(Application.class.getResource(UNCHECKED_PATH));
        checkedImage = new ImageIcon(Application.class.getResource(CHECKED_PATH));
        waitImage = new ImageIcon(Application.class.getResource(WAIT_PATH));
        waitLongImage = new ImageIcon(Application.class.getResource(WAIT_LONG_PATH));

        thirdPanel.setBackground(Color.WHITE);
        setBackground(Color.WHITE);

        StringBuffer buffer = new StringBuffer("");

        ButtonGroup buttonGroup = new ButtonGroup();

        for(int i = 0 ;i< 25;i++) {
            buffer.append(i+1);

            JRadioButton button = new JRadioButton(buffer.toString());
            button.setBackground(Color.WHITE);
            button.setIcon(uncheckedImage);
            button.setRolloverIcon(waitImage);
            button.setPressedIcon(waitLongImage);
            button.setSelectedIcon(checkedImage);
            buttonGroup.add(button);
            thirdPanel.add(button);

            buffer.delete(0,buffer.capacity());
        }
        tabbedPane.add("RadioButtons",thirdPanel);



    }

    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == toTheLeft) {

                for(int i = 0;i < rightListModel.size();i++) {
                    if(rightList.isSelectedIndex(i)) {
                        leftListModel.add(leftListModel.size() ,rightListModel.get(i));
                        rightListModel.remove(i);
                        i = -1;
                    }

                }

            }
            else if (e.getSource() == toTheRight) {
                for(int i = 0;i < leftListModel.size();i++) {
                    if(leftList.isSelectedIndex(i)) {
                        rightListModel.add(rightListModel.size() ,leftListModel.get(i));
                        leftListModel.remove(i);
                        i = -1;
                    }


                }

            }
        }
    }
    public class CustomMouseListener extends MouseAdapter {
        public static final String FONT_STYLE = "Calibri";
        public static final int FONT_SIZE = 10;
        private Color color;
        private JButton button;
        private Font font = new Font(FONT_STYLE,Font.BOLD,FONT_SIZE);
        @Override
        public void mouseEntered(MouseEvent e) {
            button = (JButton)e.getSource();
            button.setFont(font);
            color = button.getBackground();
            button.setBackground(changerColor);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            button = (JButton)e.getSource();
            button.setFont(font);
            button.setBackground(color);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            button = (JButton)e.getSource();
            button.setFont(font);
            button.setText("Clicked!");
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            button = (JButton)e.getSource();
            button.setFont(font);
            button.setText("");
        }
    }

}
