package com.arkanoid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LENOVO on 31.01.2018.
 */
public class Platform {
    private ImageIcon image;
    private Color color;
    private int width;
    private int height;
    private Point center;

    public Platform(Color color, int width, int height, Point center) {
        this.color = color;
        this.width = width;
        this.height = height;
        this.center = center;
    }

    public Platform(ImageIcon image, Color color, int width, int height, Point center) {

        this.image = image;
        this.color = color;
        this.width = width;
        this.height = height;
        this.center = center;
    }
    public void changePlatformPosition(int xPos,int yPos) {
        Point point = new Point(xPos,yPos);
        center = point;
    }


    public Point getCenter() {
        return center;
    }
}
