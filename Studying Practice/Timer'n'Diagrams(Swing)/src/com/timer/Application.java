package com.timer;

import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Application extends JFrame {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 500;
    private GraphicalPanel graphicalPanel;
    private ImageMovingPanel imageMovingPanel;
    private DiagramApplicationFrame diagramFrame;
    private JDialog clockDialog;
    private JDialog imageMovingDialog;
    private final String START_TITLE = "Start";
    private final String CLOCK_TITLE = "Tic - Tac";
    private final String IMAGE_ROUND_MOVE_TITLE= "Image Round Moving";
    private final String DIAGRAM_TITLE = "Round Diagram";
    private final String TITLE = "Hm, Animation Basics?";
    private JMenu menuOne;
    private JMenuBar menuBar;
    private JMenuItem firstMenuItem;
    private JMenu menuTwo;
    private JMenuItem secondMenuItem;
    private JMenu menuThree;
    private JMenuItem thirdMenuItem;



    public void setScreenBounds() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (int)screenSize.getWidth() / 2 - WIDTH / 2;
        int y = (int)screenSize.getHeight() / 2 - HEIGHT / 2;
        setBounds(x,y,WIDTH,HEIGHT);
    }
    Application() {
        setResizable(false);
        setScreenBounds();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(TITLE);
        MenuListener menuListener = new MenuListener();

        menuOne = new JMenu(CLOCK_TITLE);
        menuTwo = new JMenu(IMAGE_ROUND_MOVE_TITLE);
        menuThree = new JMenu(DIAGRAM_TITLE);

        firstMenuItem = new JMenuItem(START_TITLE);
        firstMenuItem.addActionListener(menuListener);
        secondMenuItem = new JMenuItem(START_TITLE);
        secondMenuItem.addActionListener(menuListener);
        thirdMenuItem = new JMenuItem(START_TITLE);
        thirdMenuItem.addActionListener(menuListener);

        menuBar = new JMenuBar();
        menuOne.add(firstMenuItem);
        menuTwo.add(secondMenuItem);
        menuThree.add(thirdMenuItem);
        menuBar.add(menuOne);
        menuBar.add(menuTwo);
        menuBar.add(menuThree);

        setJMenuBar(menuBar);



        graphicalPanel = new GraphicalPanel();
        clockDialog = new JDialog();
        createDialog(clockDialog,false,getBounds(),CLOCK_TITLE,graphicalPanel);

        imageMovingDialog = new JDialog();
        imageMovingPanel = new ImageMovingPanel();
        createDialog(imageMovingDialog,true,getBounds(),IMAGE_ROUND_MOVE_TITLE,imageMovingPanel);

        diagramFrame = new DiagramApplicationFrame(DIAGRAM_TITLE);
        diagramFrame.pack();
        diagramFrame.setResizable(false);
        RefineryUtilities.centerFrameOnScreen(diagramFrame);







    }
    public void createDialog(JDialog dialog,boolean isResizable,Rectangle bounds,String title,JPanel panel) {
        dialog.setResizable(isResizable);
        dialog.setBounds(bounds);
        dialog.setTitle(title);
        dialog.add(panel);
    }
    public class MenuListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == firstMenuItem) {
                clockDialog.setVisible(true);
            }
            else if(e.getSource() == secondMenuItem) {
                imageMovingDialog.setVisible(true);
            }
            else if(e.getSource() == thirdMenuItem) {
                diagramFrame.setVisible(true);
            }
        }
    }

}
