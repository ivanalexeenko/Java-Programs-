package com.arkanoid;

import java.io.IOException;

/*
 * Created by LENOVO on 31.01.2018.
 */
public class Main {
    public static void main(String[] args) {
        Application application = null;
        try {
            application = new Application();
        } catch (IOException e) {
            e.printStackTrace();
        }
        application.setVisible(true);

    }
}
