package com.drawings;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Application extends JFrame {

    public static final int DEFAULT_WIDTH = 850;
    public static final int DEFAULT_HEIGHT = 650;
    public static String DEFAULT_CAPTION = "Kinda Paint =)";
    public static final String ICON_NAME = "Paint_3D_icon.png";
    private JScrollPane scrollPane;
    private JColorChooser colorChooser;
    private JFileChooser fileChooser;

    public void setScreenBounds(int width,int height) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (int)(screenSize.getWidth() / 2) - (width / 2);
        int y = (int)(screenSize.getHeight() / 2) - (height / 2);
        setBounds(x,y,width,height);
    }

    Application() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setScreenBounds(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setTitle(DEFAULT_CAPTION);

        GraphicalPanel graphicalPanel = new GraphicalPanel();
        scrollPane = new JScrollPane(graphicalPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane);


        Image image = new ImageIcon(Application.class.getResource(ICON_NAME)).getImage();
        setIconImage(image);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);
        JMenu saveMenu = new JMenu("Save");
        menuBar.add(saveMenu);
        JMenu openMenu = new JMenu("Open");
        menuBar.add(openMenu);

        JMenuItem item = new JMenuItem("Color Chooser");
        menu.add(item);
        JMenuItem chooseToSave = new JMenuItem("Choose place to Save...");
        saveMenu.add(chooseToSave);
        JMenuItem chooseToOpen = new JMenuItem("Choose file to Open...");
        openMenu.add(chooseToOpen);

        colorChooser = new JColorChooser();
        fileChooser = new JFileChooser();

        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == item) {
                    GraphicalPanel.color = JColorChooser.showDialog(colorChooser, "Choose a color", Color.cyan);
                }
                else if (e.getSource() == chooseToSave) {
                    if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        BufferedImage bufferedImage = new BufferedImage(GraphicalPanel.PANEL_WIDTH, GraphicalPanel.PANEL_HEIGHT, BufferedImage.TYPE_INT_RGB);
                        graphicalPanel.paint(bufferedImage.getGraphics());
                        try {
                            ImageIO.write(bufferedImage, "png", file);
                        } catch (IOException e1) {
                            //Create;
                        }
                    }

                }
                else if (e.getSource() == chooseToOpen) {
                    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        try {
                            GraphicalPanel.screenImage = ImageIO.read(file);
                            graphicalPanel.repaint();
                        } catch (IOException e1) {
                            //Create;
                        }
                    }
                }
            }
        };
        item.addActionListener(listener);
        chooseToSave.addActionListener(listener);
        chooseToOpen.addActionListener(listener);

    }



}
