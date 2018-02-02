package com.control;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Figures {
    private ArrayList<Rectangle> figures;

    public Figures() {
        figures = new ArrayList<Rectangle>();
    }

    public void read(File file) throws FileNotFoundException,NoSuchElementException,NumberFormatException {

        figures.clear();
        Scanner scanner = new Scanner(file);
        int data1 = 0;
        int data2 = 0;
        int data3 = 0;
        int data4 = 0;

        while(scanner.hasNext()) {
            data1 = scanner.nextInt();
            data2 = scanner.nextInt();
            data3 = scanner.nextInt();
            data4 = scanner.nextInt();
            Rectangle figure = new Rectangle(new Point(data1, data2), new Point(data3, data4));
            figures.add(figure);
        }


    }
    public void sort() {
        ArrayList arrayList = new ArrayList();
        figures.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Rectangle figure1 = (Rectangle) o1;
                Rectangle figure2 = (Rectangle) o2;
                return ((Rectangle) o2).compareTo((Rectangle) o1);
            }
        });

    }
    public void add(Rectangle figure) {
        figures.add(figure);
    }
    public void clear() {
        figures.clear();
    }
    public Iterator iterator() {
        return figures.iterator();
    }
    public int size() {
        return figures.size();
    }
}
