package com.arkanoid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by LENOVO on 31.01.2018.
 */
public class Ball {
    private ImageIcon image;
    private Color color;
    private double radius;
    private Point center;

    public Ball() {
        image = null;
        color = null;
        radius = 0.0;
        center = null;
    }
    public Ball(Color color,double radius,Point center) {
        this.image = null;
        this.color = color;
        this.radius = radius;
        this.center = center;
    }

    public Point getCenter() {
        return center;
    }

    public Ball(ImageIcon image, Color color, double radius, Point center) {
        this.image = image;
        this.color = color;
        this.radius = radius;
        this.center = center;
    }
    public void changeBallPosition(int xPos,int yPos) {
        Point point = new Point(xPos,yPos);
        center = point;
    }

}
