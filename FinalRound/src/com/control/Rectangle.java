package com.control;

import java.awt.*;

public class Rectangle extends Figure {
    private Point thirdPoint;
    private Point fourthPoint;

    public Rectangle(Point firstPoint, Point secondPoint) {
        super(firstPoint, secondPoint);
        thirdPoint = new Point();
        fourthPoint = new Point();
        thirdPoint.x = firstPoint.x;
        thirdPoint.y = secondPoint.y;

        fourthPoint.x = secondPoint.x;
        fourthPoint.y = firstPoint.y;


    }

    public Point getThirdPoint() {
        return thirdPoint;
    }

    public Point getFourthPoint() {
        return fourthPoint;
    }

    @Override
    public boolean equals(Object obj) {
        Rectangle rectangle = (Rectangle)obj;
        return (this.getSquare() == rectangle.getSquare());
    }

    
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(super.toString());
        buffer.deleteCharAt(buffer.length() - 1);
        buffer.append("||Third Point:");
        buffer.append(this.thirdPoint.x);
        buffer.append(";");
        buffer.append(this.thirdPoint.y);
        buffer.append("||Fourth Point:");
        buffer.append(this.fourthPoint.x);
        buffer.append(";");
        buffer.append(this.fourthPoint.y);
        buffer.append("]");
        return buffer.toString();
    }

    @Override
    public double getSquare() {
        double first =  Math.abs(firstPoint.y - thirdPoint.y);
        double second = Math.abs(firstPoint.x - fourthPoint.x);
        double square = first * second;
        return square;
    }

}
