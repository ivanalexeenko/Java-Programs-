package com.control;

import java.awt.*;

abstract public class Figure implements Comparable<Figure> {


    protected Point firstPoint;
    protected Point secondPoint;

    public Figure(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }


    @Override
    public boolean equals(Object obj) {
        Figure figure = (Figure)obj;
        if(this.firstPoint.equals(((Figure) obj).firstPoint) && this.secondPoint.equals(((Figure) obj).secondPoint) ) {
            return true;
        }
        else {
            return false;
        }

    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("[First Point:");
        buffer.append(this.firstPoint.x);
        buffer.append(",");
        buffer.append(this.firstPoint.y);
        buffer.append("||Second Point:");
        buffer.append(this.secondPoint.x);
        buffer.append(",");
        buffer.append(this.secondPoint.y);
        buffer.append("||Square:");
        buffer.append(this.getSquare());
        buffer.append("]");
        return buffer.toString();
    }
    abstract public double getSquare();

    @Override
    public int compareTo(Figure o) {
        return (int) (getSquare() - o.getSquare());
    }
}
