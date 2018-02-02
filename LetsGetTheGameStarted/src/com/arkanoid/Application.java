package com.arkanoid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LENOVO on 31.01.2018.
 */
public class Application extends JFrame {

    public static String CAPTION = "Arkanoid: the Game";
    public static int WIDTH = 750;
    public static int HEIGHT = 600;


    public void centralizeWindow(int width,int height) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - width) / 2;
        int y = (screenSize.height - height) / 2;
        this.setBounds(x,y,width,height);
    }


    public Application() throws HeadlessException {
        centralizeWindow(Application.WIDTH,Application.HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        setTitle(Application.CAPTION);
        Gameplay gameplay = new Gameplay();
        setLayout(new BorderLayout());
        add(gameplay,BorderLayout.CENTER);

    }

}
