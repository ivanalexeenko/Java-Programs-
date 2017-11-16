package com.series;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

abstract public class Series {
    private int amount;

    public abstract double calculate(int index);
    public double sum(int index) {
        this.amount = index;
        double sum = 0;
        for(int i = 1;i <= this.amount;i++) {
            sum += calculate(i);
        }
        return sum;
    };
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 1;i <= this.amount;i++) {
            stringBuffer.append(calculate(i));
            stringBuffer.append(" ");
        }
        return stringBuffer.toString();
    }
    public void fileOutput(String filename) {
        String errorMessage = "";
        File file = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filename);
            fileWriter.write(toString());
        }
        catch (IOException e) {
            errorMessage = "File not found:" + e.getMessage();
        }
        finally {
            System.out.println(errorMessage);
            try {
                fileWriter.close();
            }
            catch (IOException e) {
                errorMessage = "File not found:" + e.getMessage();
            }
            finally {
                System.out.println(errorMessage);
            }
        }

    }

}
