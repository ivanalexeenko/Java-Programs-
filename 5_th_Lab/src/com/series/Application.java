package com.series;
import javax.swing.*;
import java.awt.*;
public class Application extends JFrame {

    Application(String caption,int x,int y,int width,int height) {
        super(caption);
        setBounds(x,y,width,height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    Application(String caption) {
        super(caption);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    Application() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
