package com.session;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class Main {
    public static final String DEFAULT_FILENAME = "C:\\Users\\LENOVO\\Java Proj\\Session_Class(Powered by XML)\\session.txt";

    public static final int DEFAULT_NUMBER = 0;
    public static final String DEFAULT_SURNAME = "Unknown";
    public static final String DEFAULT_SUBJECT = "Unknown";
    public static final int DEFAULT_MARK = 0;

    public static void main(String[] args) {
        System.out.println(new PatternChecker().isPattern("Tarantino",PatternChecker.CUSTOM_PATTERN));
        Application application = new Application();
        application.setVisible(true);
    }
}
