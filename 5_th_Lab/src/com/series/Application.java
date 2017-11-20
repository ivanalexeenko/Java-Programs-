package com.series;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Application extends JFrame {
    public static final int DEFAULT_WIDTH = 650;
    public static int DEFAULT_HEIGHT = 450;
    public static String DEFAULT_CAPTION = "LALALALALA Series LALALALALA";

    Application() {
        super();
        setTitle(DEFAULT_CAPTION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Screen Size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width / 2) - (DEFAULT_WIDTH / 2);
        int y = (screenSize.height / 2) - (DEFAULT_HEIGHT / 2);
        setBounds(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //Characteristics
        setVisible(true);
        setResizable(false);
        //Set BackGround

        GraphicalPanel graphicalPanel = new GraphicalPanel();

        add(graphicalPanel);


        //Set Window Icon
        Image image = new ImageIcon(Application.class.getResource("apple.png")).getImage();
        setIconImage(image);


    }


}
