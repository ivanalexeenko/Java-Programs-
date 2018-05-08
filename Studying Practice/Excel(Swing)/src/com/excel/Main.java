package com.excel;

import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Main {

    public static void main(String[] args) {
        Application application = new Application();
        application.setVisible(true);
        application.pack();
        new Main().setScreenBounds(application);
    }
    public void setScreenBounds(Application application) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = screenSize.width / 2 - application.getWidth() / 2;
        int y = screenSize.height / 2 - application.getHeight() / 2;
        application.setBounds(x,y,application.getWidth(),application.getHeight());
    }
}
