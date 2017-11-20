package com.series;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

abstract public class Series {
    private int amount;

    public abstract double calculate(int index) throws MyException;
    public double sum(int index) throws MyException {
        try {
            this.amount = index;
            if(index < 0) {
                throw new MyException("Wrong index,index should be a positive number.");
            }
        }
        catch (MyException e) {
            System.out.println(e.getMessage());
        }

        double sum = 0;
        if(index == 0) {
            return sum;
        }
        for(int i = 1;i <= this.amount;i++) {
            sum += calculate(i);
        }
        return sum;
    };
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 1;i <= this.amount;i++) {
            try {
                stringBuffer.append(calculate(i));
            } catch (MyException e) {
                System.out.println(e.getMessage());
            }
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
