package com.series;

import javax.swing.*;
import java.awt.*;

public class Main {
    static  public void main(String[] args) {
        Liner liner = new Liner(2,5);
        try {
            System.out.println(liner.sum(2));
        }
        catch (MyException e) {
            System.out.println(e.getMessage());
        }

        Exponential exponential = new Exponential(3,2);
        try {
            System.out.println(exponential.sum(3));
        }
        catch (MyException e) {
            System.out.println(e.getMessage());
        }
        // Start Application
        Application application = new Application();

        // Fonts For Use
        /*String [] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for(int i = 0;i < fonts.length;i++) {
            System.out.println(fonts[i]);
        }*/






    }
}
