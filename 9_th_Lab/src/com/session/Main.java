package com.session;

import java.io.FileWriter;
import java.io.IOException;
import java.util.TreeSet;

public class Main {
    public static final String DEFAULT_FILENAME = "session.txt";

    public static final int DEFAULT_NUMBER = 0;
    public static final String DEFAULT_SURNAME = "Unknown";
    public static final String DEFAULT_SUBJECT = "Unknown";
    public static final int DEFAULT_MARK = 0;

    public static void main(String[] args) {
        Application application = new Application();
        application.setVisible(true);

    }
}
