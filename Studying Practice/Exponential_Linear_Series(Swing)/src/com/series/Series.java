package com.series;

import java.io.*;

abstract public class Series {
    private int amount;

    public abstract double calculate(int index) throws MyException;

    public double sum(int index) throws MyException {
        double sum = 0;
        if (index <= 0) {
            this.amount = 0;
            return sum;
        } else {
            this.amount = index;
            for (int i = 1; i <= this.amount; i++) {
                sum += calculate(i);
            }
            return sum;
        }

    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i <= this.amount; i++) {
            try {
                stringBuffer.append(calculate(i));
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }

    public void fileOutput(String filename) throws IOException {
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(filename);
        fileWriter.write(this.toString());
        fileWriter.close();



    }

}



